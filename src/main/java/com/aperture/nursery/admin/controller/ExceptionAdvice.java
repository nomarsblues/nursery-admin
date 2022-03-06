package com.aperture.nursery.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public HttpResponse<Void> common(Exception e) {
        log.error("common exception", e);
        return HttpResponse.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<Void> validate(MethodArgumentNotValidException e) {
        log.error("validate exception", e);
        StringBuffer sb = new StringBuffer();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        allErrors.forEach(msg -> sb.append(msg.getDefaultMessage()).append(";"));
        return HttpResponse.error(sb.toString());
    }
}
