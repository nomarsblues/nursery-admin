package com.tencent.wxcloudrun.repo.jpa.entity;

import com.tencent.wxcloudrun.repo.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "article")
public class ArticleEntity extends BaseEntity {
    @Column(name = "title", length = 128, nullable = false)
    private String title;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "image", length = 256)
    private String image;
    @Lob
    @Column(name = "content")
    private String content;
    @Column(name = "description", length = 1024)
    private String description;
}
