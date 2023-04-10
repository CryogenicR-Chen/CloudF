package com.cloud.param;

import com.cloud.entity.BlogUserBehavior;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsResultParam{

    private List<BlogUserBehavior> res;

    private int total;


}