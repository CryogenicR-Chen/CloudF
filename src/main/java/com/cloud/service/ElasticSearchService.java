package com.cloud.service;

import com.cloud.base.RestResponse;
import com.cloud.param.EsResultParam;

public interface ElasticSearchService {

    RestResponse<?> createIndex(String index);

    RestResponse<?> isIndexExist(String index);

    RestResponse<?> delete(String index);

    RestResponse<?> insertDocument(String index, String id, Object obj);

    RestResponse<?> deleteDocument(String index, String id);

    RestResponse<EsResultParam> searchDocument(String index, String name, String context, int from, int size);

    RestResponse<EsResultParam> searchDocument(String index, String name, String context);

    RestResponse<?> synchronization();

}
