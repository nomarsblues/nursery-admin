package com.tencent.wxcloudrun.application;

import com.aperture.nursery.admin.domain.article.Article;
import com.aperture.nursery.admin.domain.article.ArticleChecker;
import com.aperture.nursery.admin.domain.article.ArticleCreator;
import com.aperture.nursery.admin.domain.article.ArticleUpdater;
import com.aperture.nursery.admin.domain.article.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleApp {
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private ArticleCreator articleCreator;
    @Autowired
    private ArticleUpdater articleUpdater;
    @Autowired
    private ArticleChecker articleChecker;
    
    @Transactional
    public void create(Article cmd) {
        articleChecker.check(cmd);
        Article article = articleCreator.build(cmd);
        articleRepo.save(article);
    }

    @Transactional
    public void update(Article cmd) {
        articleChecker.check(cmd);
        Article ori = articleRepo.findById(cmd.getId());
        Article Article = articleUpdater.build(cmd, ori);
        articleRepo.save(Article);
    }

    @Transactional
    public void offline(Long id) {
        Article ori = articleRepo.findById(id);
        ori.offline();
        articleRepo.save(ori);
    }

    @Transactional
    public void online(Long id) {
        Article ori = articleRepo.findById(id);
        ori.online();
        articleRepo.save(ori);
    }

    public List<Article> listAllForClient() {
        return articleRepo.query(ArticleRepo.Query.builder().status(0).build());
    }

    public List<Article> listAll() {
        return articleRepo.query(ArticleRepo.Query.builder().build());
    }
}
