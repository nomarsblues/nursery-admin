package com.aperture.nursery.admin.domain.announce;

import com.aperture.nursery.admin.meta.Creator;
import org.springframework.stereotype.Component;

@Component
public class AnnouceCreator implements Creator<Announce, Announce> {
    @Override
    public Announce build(Announce announce) {
        announce.online();
        return announce;
    }
}
