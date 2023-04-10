package com.cloud.controller;

import com.cloud.base.Session;
import com.cloud.entity.UserBase;
import com.cloud.param.LoginParam;
import com.cloud.param.RegisterParam;
import com.cloud.base.RestResponse;
import com.cloud.service.impl.LoginServiceImpl;
import com.cloud.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/register")
@Api(value = "User register")
public class RegisterController {

    @Autowired
    LoginServiceImpl loginService;

    @RequestMapping(value = "mail", method = RequestMethod.POST)
    @ApiOperation(value = "first register")
    public String mailRegister(RegisterParam registerParam, Model model, HttpServletResponse response) {
        UserBase obj = loginService.mailRegister(registerParam, model, response).getObj();
        ResponseUtils.setUserResponse(obj, model, response);
        return "forward:/index";
    }


}
