package com.cloud.controller;

import com.cloud.base.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @RequestMapping(value = "/noAuthentication", method = RequestMethod.GET)
    public RestResponse<?> changeInfo(Object obj) {return RestResponse.error("no authentication"); }
    @RequestMapping(value = "/noAuthentication", method = RequestMethod.POST)
    public RestResponse<?> changeInfo2(Object obj) {
        return RestResponse.error("no authentication");
    }

}
