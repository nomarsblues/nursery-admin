package com.aperture.nursery.admin.domain.user;

import com.aperture.nursery.admin.common.context.SessionContext;
import com.aperture.nursery.admin.domain.user.repo.UserRepo;
import com.aperture.nursery.admin.meta.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public Long getUserId() {
        String wxId = SessionContext.get().getWxId();
        wxId = "test"; // mock
        if (StringUtils.isEmpty(wxId)) {
            throw new ServiceException("获取用户登陆态失败");
        }
        User user = userRepo.findByWxId(wxId).orElse(null);
        if (user != null) {
            return user.getId();
        }
        user = new User();
        user.setWxId(wxId);
        userRepo.save(user);
        return user.getId();
    }
}
