package com.aperture.nursery.admin.domain.enroll;

import com.aperture.nursery.admin.meta.Checker;
import com.aperture.nursery.admin.meta.exception.CheckException;
import org.springframework.stereotype.Component;

@Component
public class EnrollChecker implements Checker<Enroll> {

    @Override
    public void check(Enroll enroll) throws CheckException {
        if (enroll.getUserId() == null) {
            throw new CheckException("未知用户");
        }
    }
}
