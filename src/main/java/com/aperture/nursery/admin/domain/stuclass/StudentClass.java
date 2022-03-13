package com.aperture.nursery.admin.domain.stuclass;

import com.aperture.nursery.admin.meta.Domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentClass implements Domain {
    private Long id;
    private String name;
}
