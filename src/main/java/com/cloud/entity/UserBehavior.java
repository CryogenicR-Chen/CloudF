package com.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehavior {
    private Long id;
    @Column(name = "blog_name")
    private String blogName;
    @Column(name = "blog_id")
    private Long blogId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "click_time")
    private Integer clickTime;
    @Column(name = "is_like")
    private Boolean isLike;
    @Column(name = "is_delete")
    private Boolean isDelete;
    @Column(name = "like_level")
    private Integer likeLevel;

    private Double rate;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "is_collect")
    private Boolean isCollect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName == null ? null : blogName.trim();
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getClickTime() {
        return clickTime;
    }

    public void setClickTime(Integer clickTime) {
        this.clickTime = clickTime;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean isLike) {
        this.isLike = isLike;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getLikeLevel() {
        return likeLevel;
    }

    public void setLikeLevel(Integer likeLevel) {
        this.likeLevel = likeLevel;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean isCollect) {
        this.isCollect = isCollect;
    }
}