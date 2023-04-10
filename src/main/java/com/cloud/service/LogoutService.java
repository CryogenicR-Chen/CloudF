package com.cloud.service;

import com.cloud.base.RestResponse;

import javax.servlet.http.HttpServletResponse;



public interface LogoutService {
    RestResponse<Object> logout(HttpServletResponse response);
}
