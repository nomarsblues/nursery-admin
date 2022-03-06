package com.aperture.nursery.admin.common.json;

import com.aperture.nursery.admin.meta.exception.SerializeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    public static String toJson(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("to json failed", e);
            throw new SerializeException("解析失败");
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("from json failed", e);
            throw new SerializeException("解析失败");
        }
    }


    public static <T> T fromJson(String json, TypeReference<T> r) {
        try {
            return OBJECT_MAPPER.readValue(json, r);
        } catch (JsonProcessingException e) {
            log.error("from json failed", e);
            throw new SerializeException("解析失败");
        }
    }
}
