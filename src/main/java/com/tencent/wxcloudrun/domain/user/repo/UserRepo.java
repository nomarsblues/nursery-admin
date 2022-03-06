package com.tencent.wxcloudrun.domain.user.repo;

import com.tencent.wxcloudrun.domain.user.User;

import java.util.Optional;

public interface UserRepo {
    boolean save(User domain);
    User findById(Long id);
    Optional<User> findByWxId(String wxId);
}
