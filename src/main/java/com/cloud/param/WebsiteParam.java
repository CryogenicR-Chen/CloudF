package com.cloud.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class WebsiteParam {
    @NotNull
    private Long id;

    private String verificationNumber;

    @NotEmpty
    private String name;

    @NotEmpty
    private String mail;

    private String phoneNumber;

    @NotEmpty
    private String password;

    private Integer score;

    private Integer ranking;

    private Byte authority;
}
