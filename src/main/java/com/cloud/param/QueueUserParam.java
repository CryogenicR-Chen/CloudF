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
public class QueueUserParam {
    private String name;

    private String context;
}
