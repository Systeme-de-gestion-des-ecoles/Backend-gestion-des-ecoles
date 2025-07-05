package com.uds.project.service_authentification_compte.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uds.project.service_authentification_compte.entity.AuthAuditLog;

@Repository
public interface AuthAuditLogRepository extends JpaRepository<AuthAuditLog, UUID> {
    List<AuthAuditLog> findByUserIdOrderByCreatedAtDesc(UUID userId);
    List<AuthAuditLog> findByEventTypeAndSuccessOrderByCreatedAtDesc(String eventType, Boolean success);
} 