package com.aperture.nursery.admin.common.http;

import org.springframework.web.client.RestTemplate;

public class HttpUtil {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static <T> T post(String url, Object request, Class<T> clazz) {
        return REST_TEMPLATE.postForObject(url, request, clazz);
    }
}
