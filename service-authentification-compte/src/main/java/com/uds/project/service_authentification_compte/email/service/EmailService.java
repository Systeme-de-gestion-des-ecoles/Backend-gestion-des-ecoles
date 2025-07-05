package com.uds.project.service_authentification_compte.email.service;

public interface EmailService {
    void sendVerificationEmail(String to, String token);
    void sendMfaCode(String to, String code);
    void sendPasswordResetEmail(String to, String token);
} 