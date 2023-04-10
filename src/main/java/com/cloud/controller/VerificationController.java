package com.cloud.controller;

import com.cloud.entity.UserBase;
import com.cloud.param.VerificationParam;
import com.cloud.service.VerificationService;
import com.cloud.base.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@Api(value = "send verification code")
public class VerificationController{

    @Autowired
    VerificationService verificationService;


//    mail gun
//    @RequestMapping(value = "/verify", method = RequestMethod.POST)
//    @ApiOperation(value = "send verification code")
//    public RestResponse<Object> send(@Valid @RequestBody VerificationParam param) {
//        return verificationService.sendVerificationNumber(param.getFrom(), param.getMail());
//    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public String send(Model model, String mail) {
        verificationService.sendVerificationNumber(mail);
        model.addAttribute("currentUser", UserBase.builder().id(1l).name("").build());
        return "index";
    }

    @RequestMapping(value = "/send/password", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public RestResponse<Object> sendPassword(@Valid @RequestBody VerificationParam param) {
        return verificationService.sendVerificationNumber(param.getMail());
    }
}
