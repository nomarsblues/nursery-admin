package com.aperture.nursery.admin.domain.user.repo;

import com.aperture.nursery.admin.domain.user.User;

import java.util.Optional;

public interface UserRepo {
    boolean save(User domain);
    User findById(Long id);
    Optional<User> findByWxId(String wxId);
}
