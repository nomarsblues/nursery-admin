package com.aperture.nursery.admin.infra.repo.jpa;

import com.aperture.nursery.admin.infra.repo.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {
}
