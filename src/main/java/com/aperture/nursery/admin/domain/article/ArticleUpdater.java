package com.aperture.nursery.admin.domain.article;

import com.aperture.nursery.admin.meta.Updater;
import org.springframework.stereotype.Component;

@Component
public class ArticleUpdater implements Updater<Article, Article> {

    @Override
    public Article build(Article article, Article ori) {
        return article;
    }
}
