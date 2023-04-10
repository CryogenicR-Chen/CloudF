package com.cloud.controller;

import com.cloud.entity.Blog;
import com.cloud.entity.UserBase;
import com.cloud.service.BlogService;
import com.cloud.service.LoginService;
import com.cloud.base.RestResponse;
import com.cloud.base.Session;
import com.cloud.param.LoginParam;
import com.cloud.param.NormalLoginParam;
import com.cloud.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping("/login")
@Api(value = "User login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    BlogService blogService;


    private static final String NORMAL_LOGIN_URL = "/normal";
    private static final String MAIL_LOGIN_URL = "/mail";

    @RequestMapping(value = NORMAL_LOGIN_URL, method = RequestMethod.POST)
    @ApiOperation(value = "login without use verification number")
    public RestResponse<UserBase> normalLogin(@Valid @RequestBody NormalLoginParam loginParam, Model model) {
        Session.getSession().setMaxInactiveInterval(5184000);
        return loginService.normalLogin(loginParam);
    }

    @RequestMapping(value = MAIL_LOGIN_URL, method = RequestMethod.POST)
    public String mailLogin(LoginParam loginParam, Model model, HttpServletResponse response, HttpServletRequest request) {
        Session.getSession().setMaxInactiveInterval(5184000);
        UserBase obj = loginService.mailLogin(loginParam).getObj();
        ResponseUtils.setUserResponse(obj, model, response);
        return "forward:/index";
    }
}
