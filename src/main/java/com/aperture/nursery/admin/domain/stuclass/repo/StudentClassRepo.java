package com.aperture.nursery.admin.domain.stuclass.repo;

import com.aperture.nursery.admin.domain.stuclass.StudentClass;
import com.aperture.nursery.admin.meta.Repo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface StudentClassRepo extends Repo<StudentClass, StudentClassRepo.Query> {
    @Builder
    @Getter
    @Setter
    public static class Query {

    }
}
