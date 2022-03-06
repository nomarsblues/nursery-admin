package com.aperture.nursery.admin.domain.announce;

import com.aperture.nursery.admin.meta.Domain;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class Announce implements Domain {
    private Long id;
    @NotEmpty(message = "公告名称不能为空")
    private String name;
    private Integer status;
    private String description;
    @NotNull(message = "开始时间不能为空")
    private Date startTime;
    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    public void online() {
        status = 0;
    }

    public void offline() {
        status = 1;
    }
}
