package com.tencent.wxcloudrun.repo.jpa.entity;

import com.tencent.wxcloudrun.repo.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(unique = true, name = "wx_id", length = 64, nullable = false)
    private String wxId;
}
