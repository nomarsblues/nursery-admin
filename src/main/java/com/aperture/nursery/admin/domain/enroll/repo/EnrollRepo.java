package com.aperture.nursery.admin.domain.enroll.repo;

import com.aperture.nursery.admin.domain.enroll.Enroll;
import com.aperture.nursery.admin.meta.Repo;
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
        private Long userId;
        private Long annoId;
    }
}
