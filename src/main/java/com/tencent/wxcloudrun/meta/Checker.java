package com.tencent.wxcloudrun.meta;

import com.aperture.nursery.admin.meta.exception.CheckException;

public interface Checker<T> {
    void check(T t) throws CheckException;
}
