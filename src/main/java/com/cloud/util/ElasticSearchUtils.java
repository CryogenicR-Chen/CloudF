package com.cloud.util;

import com.alibaba.fastjson.JSON;

import com.cloud.entity.UserBase;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("all")
public class ElasticSearchUtils {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public boolean createIndex(String index){
        CreateIndexRequest request = new CreateIndexRequest(index);
        try{
            restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public boolean isIndexExist(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            return false;
        }
    }

    public boolean delete(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }


    public boolean deleteDocument(String index, String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        request.timeout("1s");
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }




}
