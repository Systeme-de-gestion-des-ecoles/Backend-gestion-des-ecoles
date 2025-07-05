package com.uds.project.service_authentification_compte.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uds.project.service_authentification_compte.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findByUserId(UUID userId);
    Optional<Session> findByRefreshTokenHash(String refreshTokenHash);
    
    @Modifying
    @Query("DELETE FROM Session s WHERE s.expiresAt < ?1")
    void deleteExpiredSessions(LocalDateTime now);
    
    @Modifying
    @Query("DELETE FROM Session s WHERE s.user.id = ?1")
    void deleteAllByUserId(UUID userId);

    @Modifying
    @Query("DELETE FROM Session s WHERE s.user.id = ?1")
    void deleteByUserId(UUID userId);
} 