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
public class Blog {
    private Long id;

    private Boolean isDelete;

    private String name;

    private Date createTime;

    private Date deleteTime;

    private Long creator;
    @Column(name = "creator_name")
    private String creatorName;

    private String tags;

    private String url;
    @Column(name = "is_hide")
    private Boolean isHide;
    @Column(name = "modify_time")
    private Date modifyTime;

    private Long modifier;

    private String modifierNames;
    @Column(name = "creator_url")
    private String creatorUrl;
    @Column(name = "is_vote")
    private Boolean isVote;
    @Column(name = "is_private")
    private Boolean isPrivate;
    @Column(name = "vote_count")
    private Long voteCount;

    private Long commentCount;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(Boolean isHide) {
        this.isHide = isHide;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public String getModifierNames() {
        return modifierNames;
    }

    public void setModifierNames(String modifierNames) {
        this.modifierNames = modifierNames == null ? null : modifierNames.trim();
    }

    public String getCreatorUrl() {
        return creatorUrl;
    }

    public void setCreatorUrl(String creatorUrl) {
        this.creatorUrl = creatorUrl == null ? null : creatorUrl.trim();
    }

    public Boolean getIsVote() {
        return isVote;
    }

    public void setIsVote(Boolean isVote) {
        this.isVote = isVote;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}