package com.cloud.service;

import com.cloud.entity.UserBase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserInfoService {



    UserBase getUser(Long id);

    UserBase getUserInfo(String id);

    String updateImage(MultipartFile smfile);

    List<UserBase> getFollower(Long id);

    List<UserBase> getSubscribed(Long id);

    Boolean isFollow(Long userId, Long targetId);

    Boolean follow(Long userId, Long targetId);

    Boolean unFollow(Long userId, Long targetId);

    void uploadUser(UserBase user);



}
