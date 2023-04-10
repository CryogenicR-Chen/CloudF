package com.cloud.service;

import com.cloud.base.RestResponse;
//import com.cloud.backend.dto.UserRankingPageDto;
//import com.cloud.backend.model.UserRankingModel;
import com.cloud.entity.Follow;
import com.cloud.entity.UserBase;
import com.cloud.param.UserInfoParam;
import com.cloud.param.UserParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserInfoService {



    UserBase getUser(Long id);

    String updateImage(MultipartFile smfile);

    List<UserBase> getFollower(Long id);

    List<UserBase> getSubscribed(Long id);

    Boolean isFollow(Long userId, Long targetId);

    Boolean follow(Long userId, Long targetId);

    Boolean unFollow(Long userId, Long targetId);

    void uploadUser(UserBase user);


}
