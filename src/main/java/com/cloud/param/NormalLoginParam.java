package com.cloud.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NormalLoginParam {

    @NotNull
    private String mail;

    @NotNull
    private String password;
}
