package com.cloud.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.cloud.util.TransUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

/***
 * @Author jianghanchen
 * @Date 1:45 2023/4/1
 ***/
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorExcel {

    @ExcelProperty("blogId")
    private String blogId;
    @ExcelProperty("userId")
    private Long userId;
    @ExcelProperty("rate")
    private Double rate;
    @ExcelProperty("realId")
    private Long realId;
//    @ExcelProperty("timestamp")
//    private Long timestamp;
//    @ExcelProperty("clickTime")
//    private Integer clickTime;
//    @ExcelProperty("isCollect")
//    private Boolean isCollect;
//    @ExcelProperty("isLike")
//    private Boolean isLike;


    public BehaviorExcel (UserBehavior userBehavior){
        blogId = TransUtils.getMd5(userBehavior.getBlogName());
        userId = userBehavior.getUserId();
        rate = userBehavior.getRate();
        realId = userBehavior.getBlogId();
//        isCollect = userBehavior.getIsCollect();
//        isLike = userBehavior.getIsLike();
//        clickTime = userBehavior.getClickTime();
//        timestamp = userBehavior.getModifyTime().getTime();
    }
    public BehaviorExcel (UserBehavior userBehavior, Long userId){
        blogId = TransUtils.getMd5(userBehavior.getBlogName());
        this.userId = userId;
        rate = userBehavior.getRate();
        realId = userBehavior.getBlogId();
//        isCollect = userBehavior.getIsCollect();
//        isLike = userBehavior.getIsLike();
//        clickTime = userBehavior.getClickTime();
//        timestamp = userBehavior.getModifyTime().getTime();
    }

    public BehaviorExcel (Blog blog, Long userId){
//        blogId = TransUtils.getMd5("Original - Lifestyle Blog Template");
        blogId = TransUtils.getMd5(blog.getName());
        this.userId = userId;
        realId = blog.getId();
//        isCollect = userBehavior.getIsCollect();
//        isLike = userBehavior.getIsLike();
//        clickTime = userBehavior.getClickTime();
//        timestamp = userBehavior.getModifyTime().getTime();
    }
}
