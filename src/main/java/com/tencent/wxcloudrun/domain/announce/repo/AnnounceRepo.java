package com.tencent.wxcloudrun.domain.announce.repo;

import com.tencent.wxcloudrun.domain.announce.Announce;
import com.tencent.wxcloudrun.meta.Repo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface AnnounceRepo extends Repo<Announce, AnnounceRepo.Query> {
    @Builder
    @Setter
    @Getter
    public static class Query {
        private List<Long> ids;
        private Integer status;
    }
}
