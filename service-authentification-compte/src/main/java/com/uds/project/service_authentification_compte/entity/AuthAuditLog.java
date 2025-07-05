package com.uds.project.service_authentification_compte.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_audit_log")
public class AuthAuditLog {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(nullable = false)
    private String eventType;
    
    @Column
    private String ipAddress;
    
    @Column(columnDefinition = "text")
    private String userAgent;
    
    @Column(nullable = false)
    private Boolean success;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp with time zone")
    private LocalDateTime createdAt;
} 