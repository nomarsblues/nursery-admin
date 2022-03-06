package com.tencent.wxcloudrun.domain.enroll;

import com.tencent.wxcloudrun.meta.Updater;
import org.springframework.stereotype.Component;

@Component
public class EnrollUpdater implements Updater<Enroll, Enroll> {

    @Override
    public Enroll build(Enroll enroll, Enroll ori) {
        return enroll;
    }
}
