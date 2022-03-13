
package com.aperture.nursery.admin.controller.admin;

import com.aperture.nursery.admin.application.ArticleApp;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.article.Article;
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
@RequestMapping("api/admin/article/")
@Validated
public class ArticleAdminController {
    @Autowired
    private ArticleApp app;

    @GetMapping("list")
    public HttpResponse<List<Article>> list() {
        return HttpResponse.success(app.listAll());
    }

    @PostMapping("create")
    public HttpResponse<Void> create(@Validated @RequestBody Article cmd) {
        app.create(cmd);
        return HttpResponse.success();
    }

    @PostMapping("update")
    public HttpResponse<Void> update(@Validated @RequestBody Article cmd) {
        app.update(cmd);
        return HttpResponse.success();
    }

    @PostMapping("online")
    public HttpResponse<Void> online(@RequestBody Article article) {
        app.online(article.getId());
        return HttpResponse.success();
    }

    @PostMapping("offline")
    public HttpResponse<Void> offline(@RequestBody Article article) {
        app.offline(article.getId());
        return HttpResponse.success();
    }

    @GetMapping("detail")
    public HttpResponse<Article> detail(@RequestParam Long id) {
        return HttpResponse.success(app.findById(id));
    }
}
