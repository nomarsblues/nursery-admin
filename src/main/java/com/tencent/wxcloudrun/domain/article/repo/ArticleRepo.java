package com.tencent.wxcloudrun.domain.article.repo;

import com.tencent.wxcloudrun.domain.article.Article;
import com.tencent.wxcloudrun.meta.Repo;
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
