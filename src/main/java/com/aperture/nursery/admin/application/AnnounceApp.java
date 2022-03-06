package com.aperture.nursery.admin.application;

import com.aperture.nursery.admin.domain.announce.AnnouceChecker;
import com.aperture.nursery.admin.domain.announce.AnnouceCreator;
import com.aperture.nursery.admin.domain.announce.AnnouceUpdater;
import com.aperture.nursery.admin.domain.announce.Announce;
import com.aperture.nursery.admin.domain.announce.repo.AnnounceRepo;
import com.aperture.nursery.admin.meta.exception.ServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
public class AnnounceApp {
    @Autowired
    private AnnouceCreator annouceCreator;
    @Autowired
    private AnnouceUpdater annouceUpdater;
    @Autowired
    private AnnounceRepo announceRepo;
    @Autowired
    private AnnouceChecker checker;

    @Transactional
    public void create(Announce cmd) {
        checker.check(cmd);
        Announce announce = annouceCreator.build(cmd);
        announceRepo.save(announce);
    }

    @Transactional
    public void update(Announce cmd) {
        checker.check(cmd);
        Announce ori = announceRepo.findById(cmd.getId());
        Announce announce = annouceUpdater.build(cmd, ori);
        announceRepo.save(announce);
    }

    @Transactional
    public void offline(Long id) {
        Announce ori = announceRepo.findById(id);
        ori.offline();
        announceRepo.save(ori);
    }

    @Transactional
    public void online(Long id) {
        Announce ori = announceRepo.findById(id);
        ori.online();
        announceRepo.save(ori);
    }

    public List<Announce> listAll() {
        return announceRepo.query(AnnounceRepo.Query.builder().build());
    }

    public Announce findNew() {
        return announceRepo.query(AnnounceRepo.Query.builder().status(0).build())
                .stream().max(Comparator.comparing(Announce::getStartTime))
                .orElse(null);
    }

    public Announce findById(Long id) {
        return announceRepo.query(AnnounceRepo.Query.builder().ids(Lists.newArrayList(id)).build()).stream()
                .findAny().orElseThrow(() -> new ServiceException("报名查询失败，id为" + id));
    }
}
