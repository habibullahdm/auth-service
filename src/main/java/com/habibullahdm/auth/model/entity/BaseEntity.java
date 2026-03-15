package com.habibullahdm.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @LastModifiedBy
    @Column(nullable = false, length = 36)
    private String updatedBy;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime updatedAt;

    @CreatedBy
    @Column(nullable = false, length = 36)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false)
    private ZonedDateTime createdAt;
}
