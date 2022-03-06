package com.tencent.wxcloudrun.controller.common;

import com.aperture.nursery.admin.common.richtext.ImageUploader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/common/")
@Slf4j
public class CommonController {
    @PostMapping("image/upload")
    public ImageResponse uploadImage(@RequestParam(value = "file")MultipartFile file) {
        try {
            String url = ImageUploader.upload(file);
            ImageResponse response = new ImageResponse();
            response.setMessage("上传成功");
            response.setUrl(url);
            response.setSuccess(1);
            return response;
        } catch (Exception e) {
            log.error("uploadImage failed, ", e);
            ImageResponse response = new ImageResponse();
            response.setMessage("上传失败");
            response.setSuccess(0);
            return response;
        }
    }

    @RequestMapping("health")
    public String health() {
        return "OK";
    }

    @Getter
    @Setter
    public static class ImageResponse {
        private String url;
        private int success;
        private String message;
    }
}
