package com.aperture.nursery.admin.controller.admin;

import com.aperture.nursery.admin.application.AnnounceApp;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.announce.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin/announce/")
@Validated
public class AnnounceAdminController {
    @Autowired
    private AnnounceApp app;

    @GetMapping("list")
    public HttpResponse<List<Announce>> list() {
        return HttpResponse.success(app.listAll());
    }

    @GetMapping("detail")
    public HttpResponse<Announce> detail(@RequestParam Long id) {
        return HttpResponse.success(app.findById(id));
    }

    @PostMapping("create")
    public HttpResponse<Void> create(@Validated @RequestBody Announce announce) {
        app.create(announce);
        return HttpResponse.success();
    }

    @PostMapping("update")
    public HttpResponse<Void> update(@Validated @RequestBody Announce announce) {
        app.update(announce);
        return HttpResponse.success();
    }

    @PostMapping("online")
    public HttpResponse<Void> online(@RequestBody Announce announce) {
        app.online(announce.getId());
        return HttpResponse.success();
    }

    @PostMapping("offline")
    public HttpResponse<Void> offline(@RequestBody Announce announce) {
        app.offline(announce.getId());
        return HttpResponse.success();
    }
}
