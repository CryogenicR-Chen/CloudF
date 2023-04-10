package com.cloud.mapper;

import com.cloud.entity.UserBase;
import com.cloud.entity.UserBaseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserBaseMapper {
    long countByExample(UserBaseExample example);

    int deleteByExample(UserBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBase record);

    int insertSelective(UserBase record);

    List<UserBase> selectByExampleWithBLOBs(UserBaseExample example);

    List<UserBase> selectByExample(UserBaseExample example);

    UserBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBase record, @Param("example") UserBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") UserBase record, @Param("example") UserBaseExample example);

    int updateByExample(@Param("record") UserBase record, @Param("example") UserBaseExample example);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKeyWithBLOBs(UserBase record);

    int updateByPrimaryKey(UserBase record);
}