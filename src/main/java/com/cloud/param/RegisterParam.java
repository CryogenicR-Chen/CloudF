package com.cloud.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {


    private String verificationNumber;

    private String name;

    @NotNull
    private String mail;

    private String phoneNumber;

    private String password;
}
