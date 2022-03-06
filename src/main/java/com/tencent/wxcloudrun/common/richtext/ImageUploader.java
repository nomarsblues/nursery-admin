package com.tencent.wxcloudrun.common.richtext;

import com.aperture.nursery.admin.common.http.HttpUtil;
import com.aperture.nursery.admin.common.json.JsonUtil;
import com.aperture.nursery.admin.meta.exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class ImageUploader {
    private static final String TOKEN = "yLw67BWdtZkKjE3OvqU5ccGsdTYqR4k7";
    private static final HttpHeaders HEADERS =  new HttpHeaders();
    private static final String URL_API = "https://sm.ms/api/v2";
    private static final String URL_GET_TOKEN = "/token";
    private static final String URL_GET_PROFILE = "/profile";
    private static final String URL_UPLOAD_IMAGE = "/upload";
    private static final String URL_UPLOAD_HISTORY = "/upload_history";
    private static final String URL_DELETE = "/delete";

    static {
        HEADERS.setContentType(MediaType.MULTIPART_FORM_DATA);
        HEADERS.setBasicAuth(TOKEN);
        HEADERS.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }

    public static String upload(MultipartFile file) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>(2);
        paramMap.add("smfile", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(paramMap, HEADERS);
        String response = HttpUtil.post(URL_API + URL_UPLOAD_IMAGE, request, String.class);
        BaseDataDTO<ImageDataDTO> dto = JsonUtil.fromJson(response, new TypeReference<BaseDataDTO<ImageDataDTO>>() {});
        if (dto.getSuccess()) {
            return dto.getData().getUrl();
        } else if ("image_repeated".equals(dto.getCode())) {
            return dto.getImages();
        } else {
            log.error("upload failed, response is {}", response);
            throw new ServiceException("上传失败");
        }
    }

    @Data
    public static class BaseDataDTO<T> {
        /**
         * Request status
         */
        private Boolean success;

        /**
         * Request status code
         */
        private String code;

        /**
         * Message
         */
        private String message;

        /**
         * data
         */
        private T data;

        /**
         * Request ID
         */
        @JsonProperty("RequestId")
        private String requestId;

        private String images;
    }

    @Data
    public static class ImageDataDTO {
        /**
         * file_id
         */
        @JsonProperty("file_id")
        private Number fileId;

        /**
         * Width
         */
        private Number width;

        /**
         * Height
         */
        private Number height;

        /**
         * Filename
         */
        private String filename;

        /**
         * Store name
         */
        @JsonProperty("storename")
        private String storeName;

        /**
         * Image Size
         */
        private Number size;

        /**
         * Image Path
         */
        private String path;

        /**
         * Image Deletion HASH
         */
        private String hash;

        /**
         * Image URL
         */
        private String url;

        /**
         * Image Deletion Link
         */
        private String delete;

        /**
         * Image Page Link
         */
        private String page;
    }
}
