package com.cloud.mapper;

import com.cloud.entity.UserBlog;
import com.cloud.entity.UserBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBlogMapper {
    long countByExample(UserBlogExample example);

    int deleteByExample(UserBlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBlog record);

    int insertSelective(UserBlog record);

    List<UserBlog> selectByExample(UserBlogExample example);

    UserBlog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBlog record, @Param("example") UserBlogExample example);

    int updateByExample(@Param("record") UserBlog record, @Param("example") UserBlogExample example);

    int updateByPrimaryKeySelective(UserBlog record);

    int updateByPrimaryKey(UserBlog record);
}