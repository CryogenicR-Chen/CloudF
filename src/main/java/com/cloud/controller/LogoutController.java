package com.cloud.controller;

import com.cloud.service.LogoutService;
import com.cloud.base.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;



@Controller
@Api(value = "User logout")
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response) {
        logoutService.logout(response);
        return "forward:/index";
    }

}
