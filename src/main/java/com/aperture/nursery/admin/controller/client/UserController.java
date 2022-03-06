package com.aperture.nursery.admin.controller.client;

import com.aperture.nursery.admin.common.http.HttpUtil;
import com.aperture.nursery.admin.common.json.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/user/")
@Slf4j
public class UserController {
    @GetMapping("open")
    public String decodeOpenid(HttpServletResponse response, String code) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        String wxspAppid = "wxb3714cd25cc77976";
        String wxspSecret = "10845956242ec074ac18e5e2b8985e02";

        // 授权（必填）
        String grant_type = "authorization_code";
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        log.info("解析code请求参数：" + params);
        // 发送请求
        String sr = HttpUtil.post("https://api.weixin.qq.com/sns/jscode2session", params, String.class);
        // 解析相应内容（转换成json对象）
        Map<String, String> json = JsonUtil.fromJson(sr, new TypeReference<Map<String, String>>() {
        });
        log.info("解析code请求结果:" + json.toString());
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key");
        String openid = json.get("openid");
        log.info("openid:" + openid);
        return openid;
    }
}
