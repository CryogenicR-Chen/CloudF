package com.cloud.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ESParam {
    private String index;

    private String field;

    private String id;

    private Object obj;
}
