package com.aperture.nursery.admin.meta;

public interface Updater<IN,OUT> {
    OUT build(IN in, OUT ori);
}
