package com.tencent.wxcloudrun.repo.jpa;

import com.tencent.wxcloudrun.repo.jpa.entity.AnnounceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnnounceRepository extends JpaRepository<AnnounceEntity, Long>, JpaSpecificationExecutor<AnnounceEntity> {
}
