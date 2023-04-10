package com.cloud.service;

import org.springframework.util.MultiValueMap;

public interface RestClientService {

    Object post(String resourceUrl, MultiValueMap<String, String> request, String username, String password);

    String sendPOSTRequest(String url, MultiValueMap<String, String> params);
}
