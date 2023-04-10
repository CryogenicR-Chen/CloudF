package com.cloud.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
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

    private Integer ranking;

    private Byte authority;


}
