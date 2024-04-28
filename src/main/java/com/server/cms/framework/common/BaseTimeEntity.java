package com.server.cms.framework.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(name = "DT_REG", updatable = false)
    private LocalDateTime regDt;

    @CreatedBy
    @Column(name = "ID_REG", updatable = false)
    private String regId;

    @LastModifiedDate
    @Column(name = "DT_UPDATE", insertable = false)
    private LocalDateTime updateDt;

    @LastModifiedBy
    @Column(name = "ID_UPDATE", insertable = false)
    private String updateId;
}