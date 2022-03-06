package com.aperture.nursery.admin.domain.article;

import com.aperture.nursery.admin.meta.Creator;
import org.springframework.stereotype.Component;

@Component
public class ArticleCreator implements Creator<Article, Article> {
    @Override
    public Article build(Article article) {
        article.online();
        return article;
    }
}
