package com.tencent.wxcloudrun.controller.client;

import com.aperture.nursery.admin.application.EnrollApp;
import com.aperture.nursery.admin.common.context.SessionContext;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.enroll.Enroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/enroll/")
@Validated
public class EnrollController {
    @Autowired
    private EnrollApp app;

    @GetMapping("list")
    public HttpResponse<List<Enroll>> list() {
        return HttpResponse.success(app.listAll());
    }

    @PostMapping("create")
    public HttpResponse<Void> create(@Validated @RequestBody Enroll enroll, String wxId) {
        app.create(enroll);
        SessionContext.get().setWxId(wxId);
        return HttpResponse.success();
    }

    @PostMapping("update")
    public HttpResponse<Void> update(@Validated @RequestBody Enroll enroll, String wxId) {
        app.update(enroll);
        SessionContext.get().setWxId(wxId);
        return HttpResponse.success();
    }
}
