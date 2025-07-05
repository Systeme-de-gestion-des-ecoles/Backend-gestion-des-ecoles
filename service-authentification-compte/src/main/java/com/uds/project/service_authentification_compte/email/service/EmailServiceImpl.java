package com.uds.project.service_authentification_compte.email.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Verify your email address");
        message.setText(String.format(
            "Please click the following link to verify your email address:\n\n" +
            "http://localhost:3000/verify-email?token=%s\n\n" +
            "This link will expire in 24 hours.",
            token
        ));
        
        try {
            mailSender.send(message);
            log.info("Verification email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send verification email to: {}", to, e);
            throw new RuntimeException("Failed to send verification email", e);
        }
    }

    @Override
    public void sendMfaCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your MFA Code");
        message.setText(String.format(
            "Your MFA code is: %s\n\n" +
            "This code will expire in 5 minutes.",
            code
        ));
        
        try {
            mailSender.send(message);
            log.info("MFA code sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send MFA code to: {}", to, e);
            throw new RuntimeException("Failed to send MFA code", e);
        }
    }

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reset your password");
        message.setText(String.format(
            "Please click the following link to reset your password:\n\n" +
            "http://localhost:3000/reset-password?token=%s\n\n" +
            "This link will expire in 1 hour.",
            token
        ));
        
        try {
            mailSender.send(message);
            log.info("Password reset email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send password reset email to: {}", to, e);
            throw new RuntimeException("Failed to send password reset email", e);
        }
    }
} 