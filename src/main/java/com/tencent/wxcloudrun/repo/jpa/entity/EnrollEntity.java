package com.tencent.wxcloudrun.repo.jpa.entity;

import com.tencent.wxcloudrun.repo.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "enroll", indexes = {@Index(name = "IDX_ANNO", columnList = "anno_id"), @Index(name = "IDX_USER", columnList = "user_id")})
@Getter
@Setter
public class EnrollEntity extends BaseEntity {
    @Column(name = "anno_id", nullable = false)
    private Long annoId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "stduent_id", length = 32)
    private String stduentId;

    @Column(name = "student_name", length = 32)
    private String studentName;

    @Column(name = "parent_info", length = 2048)
    private String parentInfo;

    @Column(name = "location", length = 1024)
    private String location;
}
