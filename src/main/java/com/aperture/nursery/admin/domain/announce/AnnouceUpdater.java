package com.aperture.nursery.admin.domain.announce;

import com.aperture.nursery.admin.meta.Updater;
import org.springframework.stereotype.Component;

@Component
public class AnnouceUpdater implements Updater<Announce, Announce> {
    @Override
    public Announce build(Announce announce, Announce ori) {
        return announce;
    }
}
