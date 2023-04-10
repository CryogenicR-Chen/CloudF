package com.cloud.util;

import org.springframework.util.DigestUtils;


public class TransUtils {
    public static String getMd5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String toLowerCase(String mail){
        mail = mail.toLowerCase();
        return mail;
    }
}
