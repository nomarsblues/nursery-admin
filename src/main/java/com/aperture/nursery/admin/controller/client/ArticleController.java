
package com.aperture.nursery.admin.controller.client;

import com.aperture.nursery.admin.application.ArticleApp;
import com.aperture.nursery.admin.controller.HttpResponse;
import com.aperture.nursery.admin.domain.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/article/")
@Validated
public class ArticleController {
    @Autowired
    private ArticleApp app;

    @GetMapping("list")
    public HttpResponse<List<Article>> list() {
        return HttpResponse.success(app.listAllForClient());
    }
}
