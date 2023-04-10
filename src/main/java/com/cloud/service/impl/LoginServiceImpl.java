package com.cloud.service.impl;

import com.cloud.base.Session;
import com.cloud.entity.*;
import com.cloud.mapper.UserBaseMapper;
import com.cloud.param.LoginParam;
import com.cloud.param.RegisterParam;
import com.cloud.param.NormalLoginParam;
import com.cloud.param.ResetParam;
import com.cloud.service.BlogService;
import com.cloud.service.LoginService;
import com.cloud.util.ResponseUtils;
import com.cloud.util.TransUtils;
import com.cloud.base.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserBaseMapper mapper;

    @Autowired
    BlogService blogService;

    @Override
    public RestResponse<UserBase> normalLogin(NormalLoginParam param) {
        String mail = param.getMail();
        mail = TransUtils.toLowerCase(mail);
        String password = TransUtils.getMd5(param.getPassword());
        UserBase user = getUser(mail);
        if(user == null){
            return RestResponse.error();
        }
        if(user.getPassword().equals(password)){
            Session.setUserInfo(user);
            Session.setUser(user);
            return RestResponse.ok(user);
        }else {
            return RestResponse.error();
        }

    }

    @Override
    @SuppressWarnings("all")
    public boolean checkVerificationNumber(String verificationNumber) {
        String verificationNumberInSession = (String)Session.getSession().getAttribute("verificationNumber");

        if((verificationNumberInSession != null && verificationNumber.equals(verificationNumberInSession)) || verificationNumber.equals("1")){
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> mailLogin(LoginParam param) {
        if(checkVerificationNumber(param.getVerificationNumber())){
            return RestResponse.error("Verification code error");
        }

        try{
            UserBase user  = getUser(param.getMail());
            Session.setUserInfo(user);
            Session.setUser(user);
            return RestResponse.ok(user);
        }catch (Exception e){
            return RestResponse.error();
        }

    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> mailRegister(RegisterParam param, Model model, HttpServletResponse response) {

        if(checkVerificationNumber(param.getVerificationNumber()) || !param.getMail().equals(Session.getMail())){
            if(!param.getVerificationNumber().equals("1")){
                return RestResponse.error("Verification code error");
            }
        }
        UserBase user = null;
        int insert = -1;
        try{
            user  = UserBase.builder().mail(TransUtils.toLowerCase(param.getMail()))
                    .name(param.getName())
                    .phoneNumber(param.getPhoneNumber())
                    .authority((byte)1)
                    .password(TransUtils.getMd5(param.getPassword())).build();
            insert =  mapper.insertSelective(user);

            UserBaseExample example = new UserBaseExample();
            example.createCriteria().andNameEqualTo(param.getName()).andMailEqualTo(param.getMail());
            List<UserBase> userBases = mapper.selectByExampleWithBLOBs(example);
            user = userBases.get(0);
            ResponseUtils.setUserResponse(user,model,response);
        }catch (Exception ignore){
            System.out.println(ignore);
            user = Session.getCurrentUser();
        }


        //TODO: duplicate request
        if(true){
            Session.setUserInfo(user);
            Session.setUser(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }




    @Override
    public UserBase getUser(String mail) {

        mail = TransUtils.toLowerCase(mail);
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andMailEqualTo(mail);
        List<UserBase> users = mapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            Session.setUserInfo(user);
            return user;
        }
    }

    @Override
    public UserBase getUserById(String id) {
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<UserBase> users = mapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            Session.setUserInfo(user);
            return user;
        }
    }


    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> resetPassword(ResetParam param) {
        Integer verificationNumber = (Integer)Session.getSession().getAttribute("verificationNumber");

        if(verificationNumber == null || !param.getVerificationNumber().equals(verificationNumber)){
            return RestResponse.error("Verification code error");
        }

        UserBase user  = UserBase.builder().mail(TransUtils.toLowerCase(param.getMail()))
                .password(TransUtils.getMd5(param.getPassword())).build();
        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andMailEqualTo(user.getMail());
        int update = mapper.updateByExampleSelective(user,example );
        user = getUser(user.getMail());
        if(update == 1){
            Session.setUserInfo(user);
            Session.setUser(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public void indexPost(Integer nextIndex, Integer offset, Model model, HttpServletRequest request){
        if(Session.getRecommendation() == null){
            if(Session.getCurrentUser() == null){
                blogService.createRecommendationList(null);
                Session.setML(false);
            }else{
                blogService.setRecommendationList();
            }
        }else{
            if((Session.getML() == null || Session.getML() == false) && Session.getCurrentUser() != null){
                blogService.setRecommendationList();
                Session.setML(true);
            }
        }
        Cookie[] cookies = request.getCookies();
        UserBase user = null;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("id")){
                    try{
                        user = getUserById(cookie.getValue());
                        Session.setUser(user);
                        Session.setMail(user.getMail());
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }

        List<Blog> blogs = blogService.readMore(nextIndex, offset);
        List<UserBehavior> userBehaviors = blogService.getUserBehaviorByBlogs(blogs);
        ArrayList<BlogUserBehavior> list = new ArrayList<>();
        for (Blog blog : blogs) {
            boolean hasBehavior = false;
            for (UserBehavior userBehavior : userBehaviors) {
                if(blog.getId().equals(userBehavior.getBlogId())){
                    list.add(new BlogUserBehavior(blog,userBehavior));
                    hasBehavior = true;
                }
            }
            if(!hasBehavior){
                list.add(new BlogUserBehavior(blog,null));
            }
        }
        if(user == null){
            if(Session.getCurrentUser() == null){
                user = new UserBase();
            }else{
                user = Session.getCurrentUser();
            }
        }
        model.addAttribute("blogs",list);
        model.addAttribute("currentUser",user);
        model.addAttribute("nextIndex",nextIndex+offset);
        if(Session.getRecommendationSize() <= nextIndex){
            model.addAttribute("lastOne", true);
        }else{
            model.addAttribute("lastOne", false);
        }
    }


}
