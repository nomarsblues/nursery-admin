package com.aperture.nursery.admin.infra.repo.jpa;

import com.aperture.nursery.admin.infra.repo.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByWxId(String wxId);
}
