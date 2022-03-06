package com.tencent.wxcloudrun.domain.enroll.repo;

import com.tencent.wxcloudrun.domain.enroll.Enroll;
import com.tencent.wxcloudrun.meta.Repo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface EnrollRepo extends Repo<Enroll, EnrollRepo.Query> {
    @Builder
    @Setter
    @Getter
    public static class Query {
        private List<Long> ids;
    }
}
