package com.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogFavorites {
    private Long id;

    @Column(name = "blog_name")
    private String blogName;

    private Long blogId;
    @Column(name = "favorites_id")
    private Long favoritesId;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "is_vote")
    private Boolean isVote;

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

    public Long getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Long favoritesId) {
        this.favoritesId = favoritesId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsVote() {
        return isVote;
    }

    public void setIsVote(Boolean isVote) {
        this.isVote = isVote;
    }
}