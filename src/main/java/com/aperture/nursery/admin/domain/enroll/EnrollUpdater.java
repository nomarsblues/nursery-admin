package com.aperture.nursery.admin.domain.enroll;

import com.aperture.nursery.admin.meta.Updater;
import org.springframework.stereotype.Component;

@Component
public class EnrollUpdater implements Updater<Enroll, Enroll> {

    @Override
    public Enroll build(Enroll enroll, Enroll ori) {
        return enroll;
    }
}
