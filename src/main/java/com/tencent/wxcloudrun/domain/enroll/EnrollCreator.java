package com.tencent.wxcloudrun.domain.enroll;

import com.aperture.nursery.admin.domain.user.UserService;
import com.aperture.nursery.admin.meta.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnrollCreator implements Creator<Enroll, Enroll> {

    @Autowired
    private UserService userService;
    @Override
    public Enroll build(Enroll enroll) {
        Long userId = userService.getUserId();
        enroll.setUserId(userId);
        return enroll;
    }
}
