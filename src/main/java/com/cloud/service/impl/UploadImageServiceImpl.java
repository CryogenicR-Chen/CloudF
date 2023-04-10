package com.cloud.service.impl;

import com.cloud.base.Session;
import com.cloud.entity.Blog;
import com.cloud.entity.BlogExample;
import com.cloud.entity.UserBase;
import com.cloud.mapper.BlogMapper;
import com.cloud.mapper.UserBaseMapper;
import com.cloud.service.UploadImageService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;



@Service
public class UploadImageServiceImpl implements UploadImageService {
    @Value("${smms.upload-url}")
    private String uploadUrl;

    @Value("${smms.delete-url}")
    private String deleteUrl;

    @Value("${smms.authorization}")
    private String authorization;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserBaseMapper mapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    public String uploadPicture(MultipartFile smfile) {
        try {
            logger.info("start upload");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            logger.info("set media type");
            MediaType mediaType = MediaType.parse("text/plain");
            logger.info("new file");
            File file = new File(Objects.requireNonNull(smfile.getOriginalFilename()));
            logger.info("copy");
            try {
                FileUtils.copyInputStreamToFile(smfile.getInputStream(), file);
            } catch (Exception e) {
                logger.info(e.getMessage());

            }
            logger.info("body");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("smfile", smfile.getName(),
                            RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            logger.info("start request");
            Request request = new Request.Builder()
                    .url(uploadUrl)
                    .method("POST", body)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                    .addHeader("Authorization", authorization)
                    .build();
            Response response = null;
            try {
                Call call = client.newCall(request);
                response  = call.execute();
            }catch (Exception e){
                System.out.println(e);
            }
            if (file.exists()) {
                file.delete();
            }
            logger.info("get response");
            String jsonString = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode url = null;
            logger.info("get url");
            if ("image_repeated".equals(jsonNode.get("code").asText())) {
                url = jsonNode.get("images");
            } else if ("Upload success.".equals(jsonNode.get("message").asText())) {
                url = jsonNode.get("data").get("url");
            }
            logger.info("completed");
            String resUrl = null != url && StringUtils.hasText(url.asText()) ? url.asText() : "";
            Session.getCurrentUser().setProfilePhoto(resUrl);
            UserBase currentUser = Session.getCurrentUser();
            int i = mapper.updateByPrimaryKeyWithBLOBs(currentUser);

            BlogExample example = new BlogExample();
            example.createCriteria().andCreatorEqualTo(currentUser.getId());
            Blog blog = new Blog();
            blog.setCreatorUrl(resUrl);
            int i1 = blogMapper.updateByExampleSelective(blog, example);

            return resUrl;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void delete() {

    }
}
