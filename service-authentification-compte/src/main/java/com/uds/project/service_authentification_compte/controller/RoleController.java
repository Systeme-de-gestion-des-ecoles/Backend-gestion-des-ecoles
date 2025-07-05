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

import com.uds.project.service_authentification_compte.dto.RoleRequest;
import com.uds.project.service_authentification_compte.dto.RoleResponse;
import com.uds.project.service_authentification_compte.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable UUID id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.createRole(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable UUID id, @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.updateRole(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> addPermissionToRole(
            @PathVariable UUID roleId,
            @PathVariable UUID permissionId) {
        return ResponseEntity.ok(roleService.addPermissionToRole(roleId, permissionId));
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> removePermissionFromRole(
            @PathVariable UUID roleId,
            @PathVariable UUID permissionId) {
        return ResponseEntity.ok(roleService.removePermissionFromRole(roleId, permissionId));
    }

    @PostMapping("/init")
    public ResponseEntity<List<RoleResponse>> initializeRoles() {
        List<RoleResponse> roles = roleService.initializeRoles();
        return ResponseEntity.ok(roles);
    }
} 