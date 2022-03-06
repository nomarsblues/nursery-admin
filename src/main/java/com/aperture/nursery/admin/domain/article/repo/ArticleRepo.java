package com.aperture.nursery.admin.domain.article.repo;

import com.aperture.nursery.admin.domain.article.Article;
import com.aperture.nursery.admin.meta.Repo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface ArticleRepo extends Repo<Article, ArticleRepo.Query> {
    @Builder
    @Setter
    @Getter
    public static class Query {
        private List<Long> ids;
        private Integer status;
    }
}
