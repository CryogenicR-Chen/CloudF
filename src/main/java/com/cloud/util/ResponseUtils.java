package com.cloud.util;

import com.alibaba.fastjson.JSON;
import com.cloud.base.Session;
import com.cloud.entity.UserBase;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


public class ResponseUtils {

    public static void setBlogsView(){

    }



    public static void setBlogResponse(UserBase obj, Model model, HttpServletResponse response){
        Session.getSession().setMaxInactiveInterval(5184000);
        model.addAttribute("user",obj);
        model.addAttribute("currentUser",obj);
        if(obj.getAuthority() != null){
            model.addAttribute("authority",true);
        }else{
            model.addAttribute("authority",false);
        }
        Cookie userFilePhoto = new Cookie("userFilePhoto", obj.getProfilePhoto());
        userFilePhoto.setMaxAge(5184000);
        userFilePhoto.setPath("/");;
        response.addCookie(userFilePhoto);

        Cookie mail = new Cookie("mail", obj.getMail());
        mail.setMaxAge(5184000);
        mail.setPath("/");
        response.addCookie(mail);

        Cookie authority = new Cookie("authority", obj.getAuthority().toString());

        authority.setMaxAge(5184000);
        authority.setPath("/");
        response.addCookie(authority);

        Cookie id = new Cookie("id", obj.getId().toString());
        id.setMaxAge(5184000);
        id.setPath("/");
        response.addCookie(id);

    }

    public static void setUserResponse(UserBase obj, Model model, HttpServletResponse response){
        Session.getSession().setMaxInactiveInterval(5184000);
        model.addAttribute("user",obj);
        model.addAttribute("currentUser",obj);
        if(obj.getAuthority() != null){
            model.addAttribute("authority",true);
        }else{
            model.addAttribute("authority",false);
        }
        Cookie userFilePhoto = new Cookie("userFilePhoto", obj.getProfilePhoto());
        userFilePhoto.setMaxAge(5184000);
        userFilePhoto.setPath("/");;
        response.addCookie(userFilePhoto);

        Cookie mail = new Cookie("mail", obj.getMail());
        mail.setMaxAge(5184000);
        mail.setPath("/");
        response.addCookie(mail);

        Cookie authority = new Cookie("authority", obj.getAuthority().toString());

        authority.setMaxAge(5184000);
        authority.setPath("/");
        response.addCookie(authority);

        Cookie id = new Cookie("id", obj.getId().toString());
        id.setMaxAge(5184000);
        id.setPath("/");
        response.addCookie(id);

    }
}
