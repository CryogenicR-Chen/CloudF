package com.cloud.util;

import com.alibaba.fastjson.JSON;
import com.cloud.base.Session;
import com.cloud.entity.UserBase;
import com.cloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@Component
public class CookieUtils {

    @Autowired
    static LoginService loginService;

    public static void getCookie(HttpServletResponse response,String mail) {
        Cookie cookie = new Cookie("mail",mail);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(5184000);
        response.addCookie(cookie);
    }
    public static UserBase getUser(HttpServletRequest request){
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
        return user;
    }

}
