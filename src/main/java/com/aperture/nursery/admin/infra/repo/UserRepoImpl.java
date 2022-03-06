package com.aperture.nursery.admin.infra.repo;

import com.aperture.nursery.admin.domain.user.User;
import com.aperture.nursery.admin.domain.user.repo.UserRepo;
import com.aperture.nursery.admin.infra.repo.jpa.UserRepository;
import com.aperture.nursery.admin.infra.repo.jpa.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo {
    @Autowired
    private UserRepository repository;

    @Override
    public boolean save(User domain) {
        UserEntity entity = RepositoryExecutor.save(repository::save, build(domain));
        domain.setId(entity.getId());
        return true;
    }

    @Override
    public User findById(Long id) {
        return build(RepositoryExecutor.findById(repository::findById, id));
    }

    @Override
    public Optional<User> findByWxId(String wxId) {
        return repository.findByWxId(wxId).map(this::build);
    }

    private UserEntity build(User domain) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(domain, entity);
        return entity;
    }

    private User build(UserEntity entity) {
        User domain = new User();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
