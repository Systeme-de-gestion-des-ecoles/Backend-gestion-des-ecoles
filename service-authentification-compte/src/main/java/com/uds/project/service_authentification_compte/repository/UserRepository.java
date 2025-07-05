package com.uds.project.service_authentification_compte.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uds.project.service_authentification_compte.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByVerificationToken(String token);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByRoleName(String roleName);
} 