package com.aperture.nursery.admin.infra.repo.jpa;

import com.aperture.nursery.admin.infra.repo.jpa.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Long>, JpaSpecificationExecutor<EnrollEntity> {
}
