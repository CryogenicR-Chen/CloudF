package com.cloud.service;

import com.cloud.base.RestResponse;

public interface VerificationService{
    RestResponse<Object> sendVerificationNumber(String mail);

    RestResponse<Object> sendVerificationNumberResetPassword(String mail);

    String getRandomNumber();

    RestResponse<Object> sendVerificationNumber(String from, String to);
}
