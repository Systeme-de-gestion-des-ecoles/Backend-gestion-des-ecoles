package com.uds.project.service_authentification_compte.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uds.project.service_authentification_compte.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByName(String name);
    List<Permission> findByResource(String resource);
    List<Permission> findByAction(String action);
    List<Permission> findByResourceAndAction(String resource, String action);
    boolean existsByName(String name);
} 