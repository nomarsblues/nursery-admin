package com.aperture.nursery.admin.infra.repo;

import com.aperture.nursery.admin.domain.article.Article;
import com.aperture.nursery.admin.domain.article.repo.ArticleRepo;
import com.aperture.nursery.admin.infra.repo.jpa.ArticleRepository;
import com.aperture.nursery.admin.infra.repo.jpa.entity.ArticleEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepoImpl implements ArticleRepo {
    @Autowired
    private ArticleRepository repository;
    @Override
    public boolean save(Article domain) {
        ArticleEntity entity = build(domain);
        RepositoryExecutor.save(repository::save, entity);
        domain.setId(entity.getId());
        return true;
    }

    @Override
    public List<Article> query(Query query) {
         Specification<ArticleEntity> specification = (root, q, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(query.getIds())) {
                predicates.add(root.get("id").in(query.getIds()));
            }
            if (query.getStatus() != null) {
                predicates.add(cb.equal(root.get("status").as(Integer.class), query.getStatus()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return RepositoryExecutor.query(repository::findAll, specification).stream().map(this::build).collect(Collectors.toList());
    }

    @Override
    public Article findById(Long id) {
        return build(RepositoryExecutor.findById(repository::findById, id));
    }

    private Article build(ArticleEntity entity) {
        Article domain = new Article();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    private ArticleEntity build(Article domain) {
        ArticleEntity entity = new ArticleEntity();
        BeanUtils.copyProperties(domain, entity);
        return entity;
    }
}
