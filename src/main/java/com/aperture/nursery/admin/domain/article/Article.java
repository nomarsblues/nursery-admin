package com.aperture.nursery.admin.domain.article;

import com.aperture.nursery.admin.meta.Domain;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Article implements Domain {
    private Long id;
    @NotEmpty(message = "标题不能为空")
    private String title;
    private Integer status;
    private String image;
    private String content;
    private String description;

    public void online() {
        status = 0;
    }

    public void offline() {
        status = 1;
    }
}
