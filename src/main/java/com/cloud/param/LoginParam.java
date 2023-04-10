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
public class LoginParam {


    private String verificationNumber;


    @NotNull
    private String mail;

}
