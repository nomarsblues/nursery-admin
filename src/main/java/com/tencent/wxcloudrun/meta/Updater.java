package com.tencent.wxcloudrun.meta;

public interface Updater<IN,OUT> {
    OUT build(IN in, OUT ori);
}
