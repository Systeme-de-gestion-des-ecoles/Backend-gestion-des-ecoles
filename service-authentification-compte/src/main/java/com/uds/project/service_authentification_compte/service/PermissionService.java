package com.uds.project.service_authentification_compte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uds.project.service_authentification_compte.entity.Permission;
import com.uds.project.service_authentification_compte.repository.PermissionRepository;
import com.uds.project.service_authentification_compte.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional
    public Permission createPermission(String name, String description, String resource, String action) {
        if (permissionRepository.existsByName(name)) {
            throw new IllegalArgumentException("Permission with name " + name + " already exists");
        }

        Permission permission = Permission.builder()
            .name(name)
            .description(description)
            .resource(resource)
            .action(action)
            .build();

        Permission savedPermission = permissionRepository.save(permission);
        log.info("Created new permission: {}", name);
        return savedPermission;
    }

    @Transactional
    public Permission updatePermission(UUID id, String name, String description) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        if (!permission.getName().equals(name) && permissionRepository.existsByName(name)) {
            throw new IllegalArgumentException("Permission with name " + name + " already exists");
        }

        permission.setName(name);
        permission.setDescription(description);

        Permission updatedPermission = permissionRepository.save(permission);
        log.info("Updated permission: {}", name);
        return updatedPermission;
    }

    @Transactional
    public void deletePermission(UUID id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        permissionRepository.delete(permission);
        log.info("Deleted permission: {}", permission.getName());
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermissionById(UUID id) {
        return permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    public List<Permission> getPermissionsByResource(String resource) {
        return permissionRepository.findByResource(resource);
    }

    public List<Permission> getPermissionsByAction(String action) {
        return permissionRepository.findByAction(action);
    }

    public List<Permission> getPermissionsByResourceAndAction(String resource, String action) {
        return permissionRepository.findByResourceAndAction(resource, action);
    }

    public List<Permission> initializeRolePermissions() {
        List<Permission> permissions = new ArrayList<>();
        
        // Permissions pour le rôle Admin
        permissions.add(createPermission("ADMIN_ALL", "Full access to all resources", "all", "all"));
        
        // Permissions pour le rôle Promoteur
        permissions.add(createPermission("COUNTRY_MANAGE", "Manage country settings", "country", "manage"));
        permissions.add(createPermission("COMPANY_APPROVE", "Approve companies", "company", "approve"));
        permissions.add(createPermission("COMPANY_REJECT", "Reject companies", "company", "reject"));
        permissions.add(createPermission("COMPANY_VIEW_ALL", "View all companies", "company", "view_all"));
        
        // Permissions pour la gestion des villes (Admin et Promoteur)
        permissions.add(createPermission("CITY_CREATE", "Create cities", "city", "create"));
        permissions.add(createPermission("CITY_UPDATE", "Update cities", "city", "update"));
        permissions.add(createPermission("CITY_DELETE", "Delete cities", "city", "delete"));
        permissions.add(createPermission("CITY_VIEW", "View cities", "city", "view"));
        
        // Permissions pour la gestion des pays (Admin uniquement)
        permissions.add(createPermission("COUNTRY_CREATE", "Create countries", "country", "create"));
        permissions.add(createPermission("COUNTRY_UPDATE", "Update countries", "country", "update"));
        permissions.add(createPermission("COUNTRY_DELETE", "Delete countries", "country", "delete"));
        permissions.add(createPermission("COUNTRY_VIEW", "View countries", "country", "view"));
        
        // Permissions pour le rôle Company Manager
        permissions.add(createPermission("COMPANY_MANAGE", "Manage company settings", "company", "manage"));
        permissions.add(createPermission("AGENCY_CREATE", "Create agencies", "agency", "create"));
        permissions.add(createPermission("AGENCY_UPDATE", "Update agencies", "agency", "update"));
        permissions.add(createPermission("AGENCY_DELETE", "Delete agencies", "agency", "delete"));
        permissions.add(createPermission("AGENCY_VIEW_ALL", "View all agencies", "agency", "view_all"));
        permissions.add(createPermission("USER_MANAGE", "Manage users", "user", "manage"));
        
        // Permissions pour le rôle Agency Manager
        permissions.add(createPermission("AGENCY_MANAGE", "Manage agency settings", "agency", "manage"));
        permissions.add(createPermission("USER_MANAGE_AGENCY", "Manage agency users", "user", "manage_agency"));
        permissions.add(createPermission("BOOKING_MANAGE", "Manage bookings", "booking", "manage"));
        
        // Permissions pour le rôle User
        permissions.add(createPermission("USER_PROFILE_VIEW", "View own profile", "profile", "view"));
        permissions.add(createPermission("USER_PROFILE_UPDATE", "Update own profile", "profile", "update"));
        permissions.add(createPermission("BOOKING_CREATE", "Create bookings", "booking", "create"));
        permissions.add(createPermission("BOOKING_VIEW", "View own bookings", "booking", "view"));
        
        return permissions;
    }
} 