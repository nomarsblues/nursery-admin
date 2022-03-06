package com.tencent.wxcloudrun.meta;

public interface Creator<IN, OUT> {
    OUT build(IN in);
}
