package com.uds.project.service_authentification_compte.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_authentification_compte.dto.LoginRequest;
import com.uds.project.service_authentification_compte.dto.RegisterRequest;
import com.uds.project.service_authentification_compte.dto.TokenResponse;
import com.uds.project.service_authentification_compte.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        TokenResponse user = authService.register(request);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/mfa/enable")
    public ResponseEntity<Void> enableMfa(@RequestParam UUID userId) {
        authService.enableMfa(userId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/mfa/disable")
    public ResponseEntity<Void> disableMfa(@RequestParam UUID userId) {
        authService.disableMfa(userId);
        return ResponseEntity.ok().build();
    }
} 