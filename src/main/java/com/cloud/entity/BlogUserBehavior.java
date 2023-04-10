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
public class BlogUserBehavior {
    private Long blogId;

    private String blogName;

    private Date createTime;

    private Long userId;

    private String userName;

    private String userUrl;

    private Long BehaviorUserId;

    private String tags;

    private String blogUrl;

    private Date modifyTime;

    private Boolean isVote;

    private Boolean isPrivate;

    private Long voteCount;

    private Long commentCount;

    private String content;

    private Integer clickTime;

    private String searchKey;

    private Blog blog;

    private Integer likeLevel;

    public BlogUserBehavior(Blog blog, UserBehavior userBehavior){
        this.blog = blog;
        blogId = blog.getId();
        blogName = blog.getName();
        blogUrl = blog.getUrl();
        userName = blog.getCreatorName();
        userId = blog.getCreator();
        userUrl = blog.getCreatorUrl();
        voteCount = blog.getVoteCount();
        commentCount = blog.getCommentCount();
        content = blog.getContent();
        isPrivate = blog.getIsPrivate();
        searchKey = blog.getContent() +" "+ blog.getCreatorName()+" " + blog.getName()+" " + blog.getTags();

        if(userBehavior != null){
            likeLevel = userBehavior.getLikeLevel();
            isVote = userBehavior.getIsLike();
            BehaviorUserId = userBehavior.getUserId();
        }else{

            isVote = false;
        }

    }

}
