package com.cloud.controller;

import com.cloud.base.Session;
import com.cloud.service.UploadImageService;
import com.cloud.util.ResponseUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/upload")
@Api(value = "upload image")
public class ImageController {

    @Autowired
    private UploadImageService service;


    // 用这个服务之前一定要开梯子！！！！！！！！！！
    @RequestMapping("")
    public String uploadImage(@RequestParam("file") MultipartFile smfile, HttpServletResponse response, Model model) {
        service.uploadPicture(smfile);
        ResponseUtils.setUserResponse(Session.getCurrentUser(),model,response);
        return "forward:/user/my";
    }
}
