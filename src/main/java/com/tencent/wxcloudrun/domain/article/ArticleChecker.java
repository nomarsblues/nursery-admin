package com.tencent.wxcloudrun.domain.article;

import com.aperture.nursery.admin.meta.Checker;
import com.aperture.nursery.admin.meta.exception.CheckException;
import org.springframework.stereotype.Component;

@Component
public class ArticleChecker implements Checker<Article> {

    @Override
    public void check(Article article) throws CheckException {

    }
}
