package com.aperture.nursery.admin.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse<T> {
    private T result;
    private String message;
    private int code;
    public static final int SUCC = 0;
    public static final int ERR = 1;

    public static <T> HttpResponse<T> success() {
        return success(null);
    }

    public static <T> HttpResponse<T> success(T result) {
        return success(result, null);
    }

    public static <T> HttpResponse<T> success(T result, String msg) {
        HttpResponse<T> response = new HttpResponse<>();
        response.setResult(result);
        response.setMessage(msg);
        return response;
    }

    public static <T> HttpResponse<T> error(String msg) {
        HttpResponse<T> response = new HttpResponse<>();
        response.setMessage(msg);
        response.setCode(ERR);
        return response;
    }
}
