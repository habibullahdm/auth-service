package com.habibullahdm.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(nullable = false, length = 36)
    private String updatedBy;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime updatedAt;

    @Column(nullable = false, length = 36)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false)
    private ZonedDateTime createdAt;
}
