package com.tencent.wxcloudrun.meta;

import java.util.List;

public interface Repo<T extends Domain, R> {
    boolean save(T t);
    List<T> query(R r);
    T findById(Long id);
}
