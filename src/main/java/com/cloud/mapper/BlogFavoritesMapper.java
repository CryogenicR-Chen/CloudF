package com.cloud.mapper;

import com.cloud.entity.BlogFavorites;
import com.cloud.entity.BlogFavoritesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BlogFavoritesMapper {
    long countByExample(BlogFavoritesExample example);

    int deleteByExample(BlogFavoritesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogFavorites record);

    int insertSelective(BlogFavorites record);

    List<BlogFavorites> selectByExample(BlogFavoritesExample example);

    BlogFavorites selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogFavorites record, @Param("example") BlogFavoritesExample example);

    int updateByExample(@Param("record") BlogFavorites record, @Param("example") BlogFavoritesExample example);

    int updateByPrimaryKeySelective(BlogFavorites record);

    int updateByPrimaryKey(BlogFavorites record);
}