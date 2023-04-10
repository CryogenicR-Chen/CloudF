package com.cloud.service;

import com.cloud.base.RestResponse;
import com.cloud.entity.UserBase;
import com.cloud.param.LoginParam;
import com.cloud.param.RegisterParam;
import com.cloud.param.NormalLoginParam;
import com.cloud.param.ResetParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface  LoginService {
    RestResponse<UserBase> normalLogin(NormalLoginParam param);

    RestResponse<UserBase> mailLogin(LoginParam param);

    UserBase getUser(String mail);

    UserBase getUserById(String id);

    RestResponse<UserBase> resetPassword(ResetParam param);

    boolean checkVerificationNumber(String verificationNumber);

    RestResponse<UserBase> mailRegister(RegisterParam param, Model model, HttpServletResponse response);

    void indexPost(Integer nextIndex, Integer offset, Model model, HttpServletRequest request);
}
