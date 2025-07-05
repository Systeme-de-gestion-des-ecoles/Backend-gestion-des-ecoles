package com.uds.project.service_authentification_compte.controller;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_authentification_compte.dto.PermissionRequest;
import com.uds.project.service_authentification_compte.dto.PermissionUpdateRequest;
import com.uds.project.service_authentification_compte.entity.Permission;
import com.uds.project.service_authentification_compte.service.PermissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody PermissionRequest request) {
        Permission permission = permissionService.createPermission(
            request.getName(),
            request.getDescription(),
            request.getResource(),
            request.getAction()
        );
        return ResponseEntity.ok(permission);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    public ResponseEntity<Permission> updatePermission(
        @PathVariable UUID id,
        @Valid @RequestBody PermissionUpdateRequest request
    ) {
        Permission permission = permissionService.updatePermission(
            id,
            request.getName(),
            request.getDescription()
        );
        return ResponseEntity.ok(permission);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_DELETE')")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<Permission> getPermissionById(@PathVariable UUID id) {
        Permission permission = permissionService.getPermissionById(id);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String name) {
        Permission permission = permissionService.getPermissionByName(name);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/resource/{resource}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<List<Permission>> getPermissionsByResource(@PathVariable String resource) {
        List<Permission> permissions = permissionService.getPermissionsByResource(resource);
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/action/{action}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<List<Permission>> getPermissionsByAction(@PathVariable String action) {
        List<Permission> permissions = permissionService.getPermissionsByAction(action);
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/resource/{resource}/action/{action}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<List<Permission>> getPermissionsByResourceAndAction(
        @PathVariable String resource,
        @PathVariable String action
    ) {
        List<Permission> permissions = permissionService.getPermissionsByResourceAndAction(resource, action);
        return ResponseEntity.ok(permissions);
    }


    @PostMapping("/init-role-permissions")
    @PreAuthorize("hasAnyAuthority('ADMIN_ALL', 'COMPANY_MANAGE')")
    public ResponseEntity<List<Permission>> initializeRolePermissions() {
        List<Permission> permissions = permissionService.initializeRolePermissions();
        return ResponseEntity.ok(permissions);
    }
} 