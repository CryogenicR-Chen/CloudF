package com.cloud.controller;

import com.cloud.base.Session;
import com.cloud.entity.*;
import com.cloud.mapper.BlogMapper;
import com.cloud.mapper.FollowMapper;
import com.cloud.mapper.UserBehaviorMapper;
import com.cloud.param.UserInfoParam;
import com.cloud.service.BlogService;
import com.cloud.service.UserInfoService;
import com.cloud.base.RestResponse;
//import com.cloud.backend.dto.UserModifyDto;
//import com.cloud.backend.dto.UserRankingPageDto;
//import com.cloud.backend.model.UserRankingModel;
import com.cloud.notation.LowPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@Api(value = "Used to modify user information")
public class InfoController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserBehaviorMapper userBehaviorMapper;

    @Autowired
    BlogService blogService;



    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "user modify itself")
    public RestResponse<UserBase> changeInfo(@Valid @RequestBody UserInfoParam param) {
        return userInfoService.changeInfo(param);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete account")
    public RestResponse<?> delete(String mail) {
        return userInfoService.deleteUser(mail);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ApiOperation(value = "get user' information by id")
    public RestResponse<?> getUserInfo(String id) {
        return userInfoService.getUserInfo(id);
    }

    @PostMapping("/image")
    @LowPermission
    @ApiOperation(value = "update profile image")
    public RestResponse<?> changeImage(MultipartFile smfile) {
        return RestResponse.ok(userInfoService.updateImage(smfile));
    }

    @RequestMapping(value = "/my", method = RequestMethod.POST)
    public String getUserPOST(Model model) {
        UserBase user = Session.getCurrentUser();
        setModel(model,user,user.getFollower(),user.getFan());
        return "user";
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String getUser(Model model,@RequestParam(value = "id", defaultValue = "") String id) {
        UserBase user = null;
        if(!id.equals("")){
            user = userInfoService.getUserInfo(id).getObj();
            model.addAttribute("myself",false);
            model.addAttribute("isFollow",userInfoService.isFollow(Session.getCurrentUser().getId(),user.getId()));
        }else{
            user = Session.getCurrentUser();
            model.addAttribute("myself",true);
        }

        setModel(model,user,user.getFollower(),user.getFan());
        return "user";
    }

    @RequestMapping(value = "/my/followers", method = RequestMethod.GET)
    public String getFollower(Model model,@RequestParam(value = "id", defaultValue = "") Long id) {
        List<UserBase> followers = userInfoService.getFollower(id);
        UserBase user = userInfoService.getUser(id);
        if(!id.equals("")){
            model.addAttribute("myself",false);
            model.addAttribute("isFollow",userInfoService.isFollow(user.getId(),Session.getCurrentUser().getId()));
        }else{
            user = Session.getCurrentUser();
            model.addAttribute("myself",true);
        }
        setFollowerModel(model, user,followers);
        model.addAttribute("index",3);
        return "follower";
    }
    @RequestMapping(value = "/my/subscribed", method = RequestMethod.GET)
    public String getSubscribed(Model model,@RequestParam(value = "id", defaultValue = "") Long id) {
        List<UserBase> subscribed = userInfoService.getSubscribed(id);
        UserBase user = userInfoService.getUser(id);
        setFollowerModel(model, user,subscribed);
        model.addAttribute("index",2);
        return "follower";
    }

    @RequestMapping(value = "/my/follow", method = RequestMethod.POST)
    @ResponseBody
    public Boolean follow(Model model,Long id) {
        return userInfoService.follow(Session.getCurrentUser().getId(),id);
    }
    @RequestMapping(value = "/my/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public Boolean unfollow(Model model,Long id) {
        return userInfoService.unFollow(Session.getCurrentUser().getId(),id);
    }



    public void setFollowerModel(Model model, UserBase user, List<UserBase> followers){
        Integer follower = user.getFan();
        Integer subscribed = user.getFollower();
        Integer collectCount = user.getBlogNumber();
        model.addAttribute("collectCount",collectCount);
        model.addAttribute("follow",subscribed);
        model.addAttribute("followed",follower);
        model.addAttribute("followers",followers);
        model.addAttribute("user",user);
        model.addAttribute("collects", new ArrayList<Blog>());
        model.addAttribute("favoritesList",new ArrayList<Blog>());
        model.addAttribute("loginUser",user);
    }


    public void setModel(Model model, UserBase user,Integer subscribed, Integer follower){
        Integer followUser = user.getFan();
        Integer followedUser = user.getFollower();


        List<Long> recommendationList = null;
        BlogExample example = new BlogExample();
        example.createCriteria().andCreatorEqualTo(user.getId());

        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(example);
        Integer collectCount = blogs.size();
        List<UserBehavior> userBehaviors = blogService.getUserBehaviorByBlogs(blogs);
        ArrayList<BlogUserBehavior> list = new ArrayList<>();
        for (Blog blog : blogs) {
            boolean hasBehavior = false;
            for (UserBehavior userBehavior : userBehaviors) {
                if(blog.getId() == userBehavior.getBlogId()){
                    list.add(new BlogUserBehavior(blog,userBehavior));
                    hasBehavior = true;
                }
            }
            if(!hasBehavior){
                list.add(new BlogUserBehavior(blog,null));
            }
        }
        model.addAttribute("collectCount",collectCount);
        model.addAttribute("follow",subscribed);
        model.addAttribute("followed",follower);
        model.addAttribute("blogs",list);
        model.addAttribute("user",user);
        model.addAttribute("collects", new ArrayList<Blog>());
        model.addAttribute("favoritesList",new ArrayList<Blog>());
        model.addAttribute("followUser",followUser);
        model.addAttribute("followedUser",followedUser);
        model.addAttribute("loginUser",user);
        model.addAttribute("index",1);
    }

}

