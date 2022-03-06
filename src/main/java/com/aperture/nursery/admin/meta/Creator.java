package com.aperture.nursery.admin.meta;

public interface Creator<IN, OUT> {
    OUT build(IN in);
}
