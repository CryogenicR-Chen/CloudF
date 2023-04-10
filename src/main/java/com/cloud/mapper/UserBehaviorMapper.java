package com.cloud.mapper;

import com.cloud.entity.UserBehavior;
import com.cloud.entity.UserBehaviorExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserBehaviorMapper {
    long countByExample(UserBehaviorExample example);

    int deleteByExample(UserBehaviorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBehavior record);

    int insertSelective(UserBehavior record);

    List<UserBehavior> selectByExample(UserBehaviorExample example);

    UserBehavior selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBehavior record, @Param("example") UserBehaviorExample example);

    int updateByExample(@Param("record") UserBehavior record, @Param("example") UserBehaviorExample example);

    int updateByPrimaryKeySelective(UserBehavior record);

    int updateByPrimaryKey(UserBehavior record);
}