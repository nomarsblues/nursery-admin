package com.aperture.nursery.admin.infra.repo;

import com.aperture.nursery.admin.common.json.JsonUtil;
import com.aperture.nursery.admin.meta.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class RepositoryExecutor {
    public static <T> T save(Consumer<T> c, T t) throws RepositoryException {
        try {
            c.accept(t);
            return t;
        } catch (Exception e) {
            log.error("{} save failed", JsonUtil.toJson(t), e);
            throw new RepositoryException("保存数据失败");
        }
    }

    public static <T, R> R query(Function<T, R> f, T t) throws RepositoryException {
        try {
            return f.apply(t);
        } catch (Exception e) {
            log.error("{} query failed", JsonUtil.toJson(t), e);
            throw new RepositoryException("查询数据失败");
        }
    }

    public static <T, R> R findById(Function<T, Optional<R>> f, T t) throws RepositoryException {
        try {
            return f.apply(t).get();
        } catch (Exception e) {
            log.error("{} findById failed", JsonUtil.toJson(t), e);
            throw new RepositoryException("查询数据失败");
        }
    }
}
