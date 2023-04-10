package com.cloud.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.cloud.entity.BehaviorExcel;

/***
 * @Author jianghanchen
 * @Date 1:48 2023/4/1
 ***/
public class BehaviorListener extends AnalysisEventListener<BehaviorExcel> {


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(BehaviorExcel data, AnalysisContext context) {
        System.out.println("解析到一条数据:"+ JSON.toJSONString(data));
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }


}

