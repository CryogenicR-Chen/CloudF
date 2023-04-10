package com.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.base.RestResponse;
import com.cloud.base.Session;
import com.cloud.entity.UserBase;
import com.cloud.service.LogoutService;
import com.cloud.util.AuthenticateUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@Service
public class LogoutServiceImpl implements LogoutService {


    @Override
    public RestResponse<Object> logout(HttpServletResponse response) {
//        UserBase obj = Session.getCurrentUser();
        Cookie userFilePhoto = new Cookie("userFilePhoto", null);
        userFilePhoto.setMaxAge(0);
        userFilePhoto.setPath("/");;
        response.addCookie(userFilePhoto);

        Cookie mail = new Cookie("mail",null);
        mail.setMaxAge(0);
        mail.setPath("/");
        response.addCookie(mail);

        Cookie authority = new Cookie("authority", null);

        authority.setMaxAge(0);
        authority.setPath("/");
        response.addCookie(authority);
//
//        Cookie username = new Cookie("username", null);
//        username.setMaxAge(0);
//        username.setPath("/");
//        response.addCookie(username);

        Cookie id = new Cookie("id", null);
        id.setMaxAge(0);
        id.setPath("/");
        response.addCookie(id);
        Cookie user = null;
//        try{
//            user = new Cookie("user", URLEncoder.encode(JSON.toJSONString(null), "utf-8"));
//        }catch (Exception ignore){}
//        user.setMaxAge(0);
//        user.setPath("/");
//        response.addCookie(user);

        AuthenticateUtils.unAuthenticate();

        return RestResponse.ok();
    }


}
