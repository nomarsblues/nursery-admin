package com.aperture.nursery.admin.application;

import com.aperture.nursery.admin.domain.enroll.Enroll;
import com.aperture.nursery.admin.domain.enroll.EnrollCreator;
import com.aperture.nursery.admin.domain.enroll.EnrollUpdater;
import com.aperture.nursery.admin.domain.enroll.repo.EnrollRepo;
import com.aperture.nursery.admin.domain.user.UserService;
import com.aperture.nursery.admin.meta.exception.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EnrollApp {
    @Autowired
    private EnrollRepo enrollRepo;
    @Autowired
    private EnrollCreator enrollCreator;
    @Autowired
    private EnrollUpdater enrollUpdater;
    @Autowired
    private UserService userService;

    @Transactional
    public void create(Enroll cmd) {
        Enroll enroll = enrollCreator.build(cmd);
        enroll.check();
        enrollRepo.save(enroll);
    }

    @Transactional
    public void update(Enroll cmd) {
        Enroll ori = enrollRepo.query(EnrollRepo.Query.builder()
                        .ids(Lists.newArrayList(cmd.getId())).build())
                .stream().findAny().orElseThrow(() -> new ServiceException("未找到报名信息，id为" + cmd.getId()));
        Enroll enroll = enrollUpdater.build(cmd, ori);
        enroll.check();
        enrollRepo.save(enroll);
    }

    public List<Enroll> listAll() {
        return enrollRepo.query(EnrollRepo.Query.builder().build());
    }

    public List<Enroll> listAllForClient() {
        Long userId = userService.getUserId();
        if (userId == null) {
            return Lists.newArrayList();
        }
        return enrollRepo.query(EnrollRepo.Query.builder().userId(userId).build());
    }

    public Enroll findById(Long id) {
        return enrollRepo.query(EnrollRepo.Query.builder()
                        .ids(Lists.newArrayList(id)).build())
                .stream().findAny().orElseThrow(() -> new ServiceException("未找到报名信息，id为" + id));
    }
}
