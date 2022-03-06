package com.tencent.wxcloudrun.controller.client;

import com.aperture.nursery.admin.application.AnnounceApp;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.announce.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/announce/")
@Validated
public class AnnounceController {
    @Autowired
    private AnnounceApp app;

    @GetMapping("index")
    public HttpResponse<Announce> index() {
        return HttpResponse.success(app.findNew());
    }
}
