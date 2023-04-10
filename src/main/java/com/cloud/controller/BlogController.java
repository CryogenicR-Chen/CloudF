package com.cloud.controller;

import com.cloud.base.RestResponse;
import com.cloud.base.Session;
import com.cloud.entity.Blog;
import com.cloud.entity.BlogUserBehavior;
import com.cloud.entity.UserBase;
import com.cloud.entity.UserBehavior;
import com.cloud.mapper.BlogMapper;
import com.cloud.param.EsResultParam;
import com.cloud.service.BlogService;
import com.cloud.service.ElasticSearchService;
import com.cloud.service.LoginService;
import com.cloud.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    LoginService loginService;

    @Autowired
    ElasticSearchService elasticSearchService;

    @RequestMapping(value="/readMore/{nextIndex}/{offset}",method= RequestMethod.GET)
    public String readMore(@PathVariable("nextIndex") Integer nextIndex, @PathVariable("offset") Integer offset, Model model){
        List<Blog> blogs = blogService.readMore(nextIndex, offset);
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
        model.addAttribute("blogs",list);
        model.addAttribute("nextIndex",nextIndex+1);
        return "index";
    }

    @RequestMapping(value="/collect",method= RequestMethod.GET)
    public String collect(Model model, @RequestParam(value = "from") String from, @RequestParam(value = "url") String url
                       , @RequestParam(value = "title", required = false) String title, @RequestParam(value = "description", required = false) String description, HttpServletRequest request){
        UserBase user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("id")){
                    try{
                        user = loginService.getUserById(cookie.getValue());
                        Session.setUser(user);
                        Session.setMail(user.getMail());
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
        if(Session.getCurrentUser() == null){
            return "login";
        }
        model.addAttribute("url",url);
        model.addAttribute("description",description);
        model.addAttribute("title",title);
        return "collect";
    }
    @RequestMapping(value = "/insert", method= RequestMethod.POST)
    public String insert(Blog blog, Model model, HttpServletResponse response){
        blogService.insertBlog(blog);
        ResponseUtils.setBlogResponse(Session.getCurrentUser(),model,response);
        List<Blog> blogs = blogService.readMore(10, 5);
        ArrayList<BlogUserBehavior> list = new ArrayList<>();
        for (Blog blog1 : blogs) {
            list.add(new BlogUserBehavior(blog1,null));
        }

        model.addAttribute("blogs",list);
        return "index";
    }

    @RequestMapping(value = "/like", method= RequestMethod.POST)
    @ResponseBody
    public Long like(Long blogId,Long voteCount,String blogName){
        return blogService.like(blogId,voteCount,blogName);
    }

    @RequestMapping(value = "/unlike", method= RequestMethod.POST)
    @ResponseBody
    public Boolean unlike(Long blogId, String blogName){
        blogService.unlike(blogId,blogName);
        return true;
    }


    @RequestMapping(value = "/cancel", method= RequestMethod.POST)
    @ResponseBody
    public Long cancel(Long blogId,Long voteCount,String blogName){
        return blogService.cancel(blogId,voteCount,blogName);
    }

    @RequestMapping(value="/find",method= RequestMethod.GET)
    public String find(Model model, @RequestParam(value = "searchKey") String searchKey){
        RestResponse<EsResultParam> blogs = elasticSearchService.searchDocument("blogs", "searchKey", searchKey);
        model.addAttribute("blogs",blogs.getObj().getRes());
        model.addAttribute("currentUser",Session.getCurrentUser());
        return "index";
    }


    @RequestMapping(value = "/click", method= RequestMethod.POST)
    @ResponseBody
    public Boolean click(Long blogId,Long userId,String blogName){
        blogService.click(blogId,userId,blogName);
        return true;
    }
    @RequestMapping(value = "/setRate", method= RequestMethod.GET)
    @ResponseBody
    public Boolean setRate(){
        blogService.setRate();
        return true;
    }
    @RequestMapping(value = "/downloadBehavior", method= RequestMethod.GET)
    @ResponseBody
    public Boolean downloadBehavior(){
        blogService.downloadBehavior();
        return true;
    }

    @RequestMapping(value = "/setRecommendationList", method= RequestMethod.GET)
    @ResponseBody
    public Boolean downloadBehavior2(){
        blogService.setRecommendationList();
        return true;
    }

}
