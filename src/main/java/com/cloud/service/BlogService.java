package com.cloud.service;

import com.cloud.base.RestResponse;
import com.cloud.entity.Blog;
import com.cloud.entity.UserBase;
import com.cloud.entity.UserBehavior;
import io.swagger.models.auth.In;

import java.util.List;

public interface BlogService {
    List<Blog> readMore(Integer nextIndex, Integer offset);

    void createRecommendationList(UserBase user);

    boolean insertBlog(Blog blog);

    boolean insertUserBehavior(Blog blog);

    Long like(Long blogId, Long voteCount,String blogName);

    Long cancel(Long blogId, Long voteCount, String blogName);

    void unlike(Long blogId, String blogName);

    void click(Long blogId, Long userId,String blogName);

    List<UserBehavior> getUserBehaviorByBlogs(List<Blog> blogs);

    void setRate();

    void downloadBehavior();

    void setRecommendationList();
}
