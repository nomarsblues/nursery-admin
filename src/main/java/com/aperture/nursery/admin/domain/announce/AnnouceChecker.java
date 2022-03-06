package com.aperture.nursery.admin.domain.announce;

import com.aperture.nursery.admin.domain.announce.repo.AnnounceRepo;
import com.aperture.nursery.admin.meta.Checker;
import com.aperture.nursery.admin.meta.exception.CheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnnouceChecker implements Checker<Announce> {

    @Autowired
    private AnnounceRepo repo;

    @Override
    public void check(Announce announce) throws CheckException {
        if (announce.getStartTime().after(announce.getEndTime())) {
            throw new CheckException("时间不合法");
        }
    }

    private void checkTime(Announce announce) {
        List<Announce> announces = repo.query(AnnounceRepo.Query.builder().build())
                .stream().sorted(Comparator.comparing(Announce::getStartTime))
                .collect(Collectors.toList());
        // todo
    }
}
