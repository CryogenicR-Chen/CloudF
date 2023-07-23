package com.cloud.service.impl;

import com.cloud.base.Session;
import com.cloud.entity.Follow;
import com.cloud.entity.FollowExample;
import com.cloud.entity.UserBase;
import com.cloud.entity.UserBaseExample;
import com.cloud.mapper.FollowMapper;
import com.cloud.mapper.UserBaseMapper;
import com.cloud.service.LoginService;
import com.cloud.service.UploadImageService;
import com.cloud.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;




@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserBaseMapper userBaseMapper;

    @Autowired
    UploadImageService uploadImageService;

    @Autowired
    LoginService loginService;

    @Autowired
    FollowMapper followMapper;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserBase getUser(Long id) {

        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andIdEqualTo(id);
        List<UserBase> users = userBaseMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            return user;
        }
    }

    public UserBase getUserInfo(String id) {

        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andIdEqualTo(Long.parseLong(id));
        List<UserBase> users = userBaseMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            return user;
        }
    }


    @Override
    public void uploadUser(UserBase user) {

        UserBaseExample example = new UserBaseExample();
        example.createCriteria().andIdEqualTo(user.getId());
        userBaseMapper.updateByExampleWithBLOBs(user, example);

    }


    @Override
    public String updateImage(MultipartFile smfile) {
        logger.info("start update");
        String url = uploadImageService.uploadPicture(smfile);
        UserBase userBase = UserBase.builder()
                .profilePhoto(url)
                .build();
        logger.info("insert url");
        return url;
    }


    @Override
    public List<UserBase> getFollower(Long id) {
        FollowExample example = new FollowExample();
        example.createCriteria().andFollowIdEqualTo(id);
        List<Follow> followers = followMapper.selectByExample(example);
        if(followers.size() == 0) return new ArrayList<>();
        UserBaseExample userBaseExample = new UserBaseExample();
        for (Follow follower : followers) {
            userBaseExample.or().andIdEqualTo(follower.getUserId());
        }
        List<UserBase> userBases = userBaseMapper.selectByExampleWithBLOBs(userBaseExample);

        return userBases;
    }

    @Override
    public List<UserBase> getSubscribed(Long id) {
        FollowExample example = new FollowExample();
        //userA follow userB
        example.createCriteria().andUserIdEqualTo(id);
        List<Follow> subscribed = followMapper.selectByExample(example);

        if(subscribed.size() == 0) return new ArrayList<>();
        UserBaseExample userBaseExample = new UserBaseExample();
        for (Follow sub : subscribed) {
            userBaseExample.or().andIdEqualTo(sub.getFollowId());
        }
        List<UserBase> userBases = userBaseMapper.selectByExampleWithBLOBs(userBaseExample);

        return userBases;
    }

    @Override
    public Boolean isFollow(Long userId, Long targetId){
        FollowExample example = new FollowExample();
        example.createCriteria().andUserIdEqualTo(userId).andFollowIdEqualTo(targetId);
        List<Follow> subscribed = followMapper.selectByExample(example);

        if(subscribed.size() == 0) return false;
        return true;
    }


    @Override
    public Boolean follow(Long userId, Long targetId){

        try{
            Follow follow = new Follow().builder().userId(userId).followId(targetId).build();
            followMapper.insert(follow);

            UserBase user = getUser(userId);
            user.setFollower(user.getFollower()+1);
            Session.setUser(user);
            uploadUser(user);

            user = getUser(targetId);
            user.setFan(user.getFan()+1);
            uploadUser(user);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean unFollow(Long userId, Long targetId){
        FollowExample example = new FollowExample();
        example.createCriteria().andUserIdEqualTo(userId).andFollowIdEqualTo(targetId);
        try{
            int i = followMapper.deleteByExample(example);


            UserBase user = getUser(userId);
            user.setFollower(user.getFollower()-1);
            Session.setUser(user);
            uploadUser(user);

            user = getUser(targetId);
            user.setFan(user.getFan()-1);
            uploadUser(user);
            if(i != 1){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
