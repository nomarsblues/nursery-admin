package com.tencent.wxcloudrun.domain.user;

import com.tencent.wxcloudrun.common.context.SessionContext;
import com.tencent.wxcloudrun.domain.user.repo.UserRepo;
import com.tencent.wxcloudrun.meta.exception.ServiceException;
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
