package com.tencent.wxcloudrun.repo.jpa;

import com.tencent.wxcloudrun.repo.jpa.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Long>, JpaSpecificationExecutor<EnrollEntity> {
}
