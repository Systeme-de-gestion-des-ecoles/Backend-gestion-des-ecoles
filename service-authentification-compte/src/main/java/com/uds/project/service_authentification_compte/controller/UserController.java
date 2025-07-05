package com.uds.project.service_authentification_compte.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_authentification_compte.dto.SuperAdminInitRequest;
import com.uds.project.service_authentification_compte.dto.UserResponse;
import com.uds.project.service_authentification_compte.entity.User;
import com.uds.project.service_authentification_compte.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment environment;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> activateUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.activateUser(id));
    }

    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> deactivateUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deactivateUser(id));
    }

    @PostMapping("/{id}/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> verifyUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.verifyUser(id));
    }

    @PostMapping("/{id}/unverify")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> unverifyUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.unverifyUser(id));
    }

    @PostMapping("/{id}/lock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> lockUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.lockUser(id));
    }

    @PostMapping("/{id}/unlock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> unlockUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.unlockUser(id));
    }

    @PostMapping("/init-super-admin")
    public ResponseEntity<UserResponse> initializeSuperAdmin(@Valid @RequestBody SuperAdminInitRequest request) {
        // Vérifier si l'application est en mode développement
        if (!environment.getProperty("spring.profiles.active", "").contains("dev")) {
            throw new IllegalStateException("Super admin initialization is only allowed in development mode");
        }
        
        User superAdmin = userService.initializeSuperAdmin(request);
        return ResponseEntity.ok(userService.mapToResponse(superAdmin));
    }
} 