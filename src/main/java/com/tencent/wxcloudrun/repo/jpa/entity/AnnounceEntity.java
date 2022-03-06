package com.tencent.wxcloudrun.repo.jpa.entity;

import com.tencent.wxcloudrun.repo.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "announce")
public class AnnounceEntity extends BaseEntity {

    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;
}