package com.cloud.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.cloud.base.Session;
import com.cloud.entity.*;
import com.cloud.mapper.BlogMapper;
import com.cloud.mapper.UserBaseMapper;
import com.cloud.mapper.UserBehaviorMapper;
import com.cloud.service.BlogService;
import com.cloud.service.ElasticSearchService;
import com.cloud.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.*;


@Service
@SuppressWarnings("all")
public class BlogImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserBehaviorMapper userBehaviorMapper;

    @Autowired
    UserBaseMapper userBaseMapper;

    @Autowired
    ElasticSearchService elasticSearchService;

    @Autowired
    RestClientService restClientService;

    @Override
    public List<Blog> readMore(Integer nextIndex, Integer offset){

        List<Long> recommendationList = null;
        if(Session.getRecommendationSize() < nextIndex + offset){
            recommendationList = Session.getRecommendation().subList(nextIndex, Session.getRecommendationSize());
        }else {
            recommendationList = Session.getRecommendation().subList(nextIndex, nextIndex + offset);
            Session.setNextIndex(nextIndex+offset);
        }
        BlogExample example = new BlogExample();
        for (Long id : recommendationList) {
            example.or().andIdEqualTo(id);
        }
        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(example);
        return blogs;
    }


    @Override
    public void createRecommendationList(UserBase user) {
        BlogExample example = new BlogExample();
        example.createCriteria().andIdIsNotNull();
        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(example);
        ArrayList<Long> list = new ArrayList<>();
        for (Blog blog : blogs) {
            list.add(blog.getId());
        }
        Session.setRecommendation(list);
    }
    @Override
    public boolean insertBlog(Blog blog){
        if(Session.getCurrentUser() == null){
            return false;
        }
        if(blog.getIsPrivate() == null){
            blog.setIsPrivate(false);
        }
        blog.setCreator(Session.getCurrentUser().getId());
        blog.setCreatorUrl(Session.getCurrentUser().getProfilePhoto());
        blog.setCreatorName(Session.getCurrentUser().getName());

        int insert = blogMapper.insertSelective(blog);
        BlogExample example = new BlogExample();
        example.createCriteria().andCreatorEqualTo(Session.getCurrentUser().getId()).andNameEqualTo(blog.getName());
        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(example);


        UserBase build = new UserBase().builder().id(Session.getCurrentUser().getId()).blogNumber(Session.getCurrentUser().getBlogNumber() + 1).build();
        userBaseMapper.updateByPrimaryKeySelective(build);
        BlogUserBehavior blogUserBehavior = new BlogUserBehavior(blogs.get(0), null);
        elasticSearchService.insertDocument("blogs",blogs.get(0).getName()+blogs.get(0).getCreator(),blogUserBehavior);


        blog = blogs.get(0);
        UserBehaviorExample userBehaviorExample = new UserBehaviorExample();
        userBehaviorExample.createCriteria().andBlogIdEqualTo(blog.getId()).andUserIdEqualTo(Session.getCurrentUser().getId());
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(userBehaviorExample);
        if(userBehaviors.size() == 0){
            UserBehavior ub = new UserBehavior().builder().isCollect(true).userId(Session.getCurrentUser().getId()).blogName(blog.getName()).blogId(blog.getId()).likeLevel(1).build();
            int i = userBehaviorMapper.insertSelective(ub);
        }else{
            UserBehavior userBehavior = userBehaviors.get(0);
            UserBehavior ub = new UserBehavior().builder().isCollect(true).userId(Session.getCurrentUser().getId()).likeLevel(1).build();

            int i = userBehaviorMapper.updateByExampleSelective(ub,userBehaviorExample);
        }
        return true;
    }

    @Override
    public boolean insertUserBehavior(Blog blog){
        UserBase currentUser = Session.getCurrentUser();
        Long id = currentUser.getId();
        String name = currentUser.getName();
        UserBehavior userBehavior = new UserBehavior();
        UserBehavior ub = userBehavior.builder().blogId(blog.getId()).blogName(blog.getName()).clickTime(1).userId(id).build();

        int insert = userBehaviorMapper.insertSelective(ub);
        return true;
    }

    @Override
    public List<UserBehavior> getUserBehaviorByBlogs(List<Blog> blogs){

        UserBehaviorExample example = new UserBehaviorExample();
        List<UserBehavior> userBehaviors = new ArrayList<>();

        try{
            for (Blog blog : blogs) {
                Long id = Session.getCurrentUser().getId();
                Long blogId = blog.getId();
                example.or().andBlogIdEqualTo(blogId)
                        .andUserIdEqualTo(id);
            }
            userBehaviors = userBehaviorMapper.selectByExample(example);
        }catch (Exception e){
            System.out.println(e);
        }
        return userBehaviors;
    }


    @Override
    public Long like(Long blogId, Long voteCount,String blogName){
        UserBase currentUser = Session.getCurrentUser();
        UserBehaviorExample example = new UserBehaviorExample();
        example.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(currentUser.getId());
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        if(userBehaviors.size() == 0){
            UserBehavior ub = new UserBehavior().builder().isLike(true).userId(currentUser.getId()).blogName(blogName).blogId(blogId).likeLevel(1).build();
            int insert = userBehaviorMapper.insertSelective(ub);
        }else{
            UserBehavior userBehavior = userBehaviors.get(0);
            UserBehavior ub = new UserBehavior().builder().isLike(true).userId(currentUser.getId()).likeLevel(1).build();

            int insert = userBehaviorMapper.updateByExampleSelective(ub,example);
        }

        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andIdEqualTo(blogId);

        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        blog.setVoteCount(blog.getVoteCount()+1);

        blogMapper.updateByExampleSelective(blog,blogExample);
        return voteCount+1;
    }
    @Override
    public Long cancel(Long blogId, Long voteCount, String blogName){
        UserBase currentUser = Session.getCurrentUser();
        UserBehaviorExample example = new UserBehaviorExample();
        example.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(currentUser.getId());
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        if(userBehaviors.size() == 0){
            UserBehavior ub = new UserBehavior().builder().isLike(false).userId(currentUser.getId()).likeLevel(0).blogName(blogName).blogId(blogId).build();
            int insert = userBehaviorMapper.insertSelective(ub);
        }else{
            UserBehavior userBehavior = userBehaviors.get(0);
            UserBehavior ub = new UserBehavior().builder().isLike(false).userId(currentUser.getId()).likeLevel(0).build();

            int insert = userBehaviorMapper.updateByExampleSelective(ub,example);
        }


        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andIdEqualTo(blogId);

        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        blog.setVoteCount(blog.getVoteCount()-1);

        blogMapper.updateByExampleSelective(blog,blogExample);
        return voteCount-1;
    }
    @Override
    public void unlike(Long blogId, String blogName){
        UserBase currentUser = Session.getCurrentUser();
        UserBehaviorExample example = new UserBehaviorExample();
        example.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(currentUser.getId());
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        if(userBehaviors.size() == 0){
            UserBehavior ub = new UserBehavior().builder().userId(currentUser.getId()).likeLevel(-1).blogName(blogName).blogId(blogId).build();
            int insert = userBehaviorMapper.insertSelective(ub);
        }else{
            UserBehavior userBehavior = userBehaviors.get(0);
            UserBehavior ub = new UserBehavior().builder().userId(currentUser.getId()).likeLevel(-1).build();

            int insert = userBehaviorMapper.updateByExampleSelective(ub,example);
        }

    }

    public void click(Long blogId, Long userId,String blogName){
        UserBehaviorExample example = new UserBehaviorExample();
        example.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        if(userBehaviors.size() == 0){
            UserBehavior ub = new UserBehavior().builder().userId(userId).clickTime(1).blogName(blogName).blogId(blogId).build();
            int insert = userBehaviorMapper.insertSelective(ub);
        }else{
            UserBehavior userBehavior = userBehaviors.get(0);
            UserBehavior ub = new UserBehavior().builder().userId(userId).clickTime(userBehavior.getClickTime()+1).build();

            int insert = userBehaviorMapper.updateByExampleSelective(ub,example);
        }

    }

    @Override
    public void setRate(){
        UserBehaviorExample example = new UserBehaviorExample();
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        for (UserBehavior userBehavior : userBehaviors) {
            double rate = 2.5;
            if(userBehavior.getIsCollect()){
                rate = 5;
            }else if(userBehavior.getIsLike()){
                rate = 3.5;
            }else if(userBehavior.getLikeLevel() == -1){
                rate = 0;
            }
            Integer clickTime = userBehavior.getClickTime();

            if(clickTime != null && clickTime > 0){
                double i = 0;
                rate += Math.log(clickTime)/Math.log(2);
                rate = rate > 5.0 ? 5.0 : rate;
            }
            userBehavior.setRate(rate);
            userBehaviorMapper.updateByPrimaryKeySelective(userBehavior);
        }

    }
    @Override
    public void downloadBehavior(){
        UserBehaviorExample example = new UserBehaviorExample();
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        ArrayList<BehaviorExcel> behaviorExcels = new ArrayList<>();
        for (UserBehavior userBehavior : userBehaviors) {
            behaviorExcels.add(new BehaviorExcel(userBehavior));
        }
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        String jsonString = JSON.toJSONString(behaviorExcels);
//        map.add("rating",jsonString);
//        String str = restClientService.sendPOSTRequest("http://127.0.0.1:8000/", map);
//        List<Double> doubles = JSON.parseArray(str, Double.class);

        File file = new File("./src/main/resources/DemoData.xlsx");
        EasyExcel.write(file.getAbsolutePath(), BehaviorExcel.class).sheet("DemoData").doWrite(behaviorExcels);
    }

    @Override
    public void setRecommendationList(){
        UserBehaviorExample example = new UserBehaviorExample();
        List<UserBehavior> userBehaviors = userBehaviorMapper.selectByExample(example);
        ArrayList<BehaviorExcel> behaviorExcels = new ArrayList<>();


        UserBaseExample userBaseExample = new UserBaseExample();
        List<UserBase> userBases = userBaseMapper.selectByExample(userBaseExample);

        BlogExample exampleb = new BlogExample();
        exampleb.createCriteria().andIdIsNotNull();
        List<Blog> blogs = blogMapper.selectByExampleWithBLOBs(exampleb);

        int count = 0;

        for (UserBase userBase : userBases) {
            for (Blog blog : blogs) {
//                behaviorExcels.add(new BehaviorExcel(blog,0l));
                if( count == 407 ) {
                    System.out.println();
                }
                count++;
                behaviorExcels.add(new BehaviorExcel(blog,userBase.getId()));
            }
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        String jsonString = JSON.toJSONString(behaviorExcels);
        map.add("rating",jsonString);
        String str = restClientService.sendPOSTRequest("http://127.0.0.1:8000/", map);

        Map<Long, Double> tmap = new TreeMap<>();
        List<Double> doubles = JSON.parseArray(str, Double.class);
        for (int i = 0; i < behaviorExcels.size(); i++) {
            if(behaviorExcels.get(i).getUserId().equals(Session.getCurrentUser().getId())){
                tmap.put(behaviorExcels.get(i).getRealId(),doubles.get(i));
            }

        }
        List<Map.Entry<Long,Double>> list = new ArrayList<Map.Entry<Long,Double>>(tmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Long, Double>>() {
            @Override
            public int compare(Map.Entry<Long, Double>o1, Map.Entry<Long, Double> o2) {
                return o2.getValue() - o1.getValue() > 0 ? 1 : -1;
            }
        });
        List<Long> longs = new ArrayList<>();
        for (Map.Entry<Long, Double> longDoubleEntry : list) {
            longs.add(longDoubleEntry.getKey());
        }
        Session.setRecommendation(longs);
//        File file = new File("./src/main/resources/DemoData.xlsx");
//        EasyExcel.write(file.getAbsolutePath(), BehaviorExcel.class).sheet("DemoData").doWrite(behaviorExcels);
    }
}
