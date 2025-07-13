package com.uds.project.service_authentification_compte.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uds.project.service_authentification_compte.dto.LoginRequest;
import com.uds.project.service_authentification_compte.dto.MfaRequest;
import com.uds.project.service_authentification_compte.dto.RefreshTokenRequest;
import com.uds.project.service_authentification_compte.dto.RegisterRequest;
import com.uds.project.service_authentification_compte.dto.TokenResponse;
import com.uds.project.service_authentification_compte.email.service.EmailService;
import com.uds.project.service_authentification_compte.entity.AuthAuditLog;
import com.uds.project.service_authentification_compte.entity.Role;
import com.uds.project.service_authentification_compte.entity.Session;
import com.uds.project.service_authentification_compte.entity.User;
import com.uds.project.service_authentification_compte.exceptions.AuthenticationException;
import com.uds.project.service_authentification_compte.repository.AuthAuditLogRepository;
import com.uds.project.service_authentification_compte.repository.RoleRepository;
import com.uds.project.service_authentification_compte.repository.SessionRepository;
import com.uds.project.service_authentification_compte.repository.UserRepository;
import com.uds.project.service_authentification_compte.security.JwtTokenProvider;
import com.uds.project.service_authentification_compte.security.MfaTokenProvider;
import com.uds.project.service_authentification_compte.exceptions.ResourceNotFoundException;
import com.uds.project.service_authentification_compte.util.RequestContextUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SessionRepository sessionRepository;
    private final AuthAuditLogRepository auditLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MfaTokenProvider mfaTokenProvider;
    private final EmailService emailService;

   @Transactional
public TokenResponse register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new AuthenticationException("Email already registered");
    }

    Role userRole = roleRepository.findByName(request.getRoleName())
        .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getRoleName()));

    User user = User.builder()
        .email(request.getEmail())
        .passwordHash(passwordEncoder.encode(request.getPassword()))
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .phone(request.getPhone())
        .isVerified(false)
        .isActive(false)
        .mfaEnabled(false)
        .role(userRole)
        .build();

    userRepository.save(user);
    log.info("New user registered: {}", user.getEmail());

    String verificationToken = jwtTokenProvider.generateEmailVerificationToken(user);
    emailService.sendVerificationEmail(user.getEmail(), verificationToken);

    return generateTokenResponse(user, false);
}


    public TokenResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            log.info("-------------------"+ user.getEmail());
            
            if (!user.isVerified()) {
                throw new AuthenticationException("Email not verified");
            }

            boolean mfaRequired = user.isMfaEnabled();
            String mfaToken = mfaRequired ? mfaTokenProvider.generateMfaToken(user) : null;

            if (mfaRequired) {
                // Send MFA code via email
                String mfaCode = mfaTokenProvider.generateMfaCode();
                emailService.sendMfaCode(user.getEmail(), mfaCode);
            }

            return generateTokenResponse(user, mfaRequired, mfaToken);
        } catch (Exception e) {
            log.error("Login failed for user: {}", request.getEmail(), e);
            throw new AuthenticationException("Invalid credentials");
        }
    }

    public TokenResponse verifyMfa(MfaRequest request) {
        User user = mfaTokenProvider.validateMfaToken(request.getMfaToken());
        if (!mfaTokenProvider.validateMfaCode(user, request.getMfaCode())) {
            throw new AuthenticationException("Invalid MFA code");
        }

        return generateTokenResponse(user, false);
    }

    public TokenResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new AuthenticationException("Invalid refresh token");
        }

        User user = userRepository.findById(jwtTokenProvider.getUserIdFromRefreshToken(refreshToken))
            .orElseThrow(() -> new ResourceNotFoundException("User not found 2"));

        return generateTokenResponse(user, false);
    }

    @Transactional
    public void verifyEmail(String token) {
        User user = jwtTokenProvider.validateEmailVerificationToken(token);
        User exiUser = userRepository.findById(
            user.getId()).orElseThrow(() -> new RuntimeException(" User not found 1"+ user));
        if(exiUser.isVerified()){
            log.info("Account aready verified");

        }
        exiUser.setVerified(true);
        exiUser.setActive(true);
        userRepository.save(exiUser);
        log.info("Email verified for user: {}", user.getEmail());
    }

@Transactional
public void logout() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            sessionRepository.deleteByUserId(user.getId());
            log.info("User logged out: {}", user.getEmail());
        } else {
            log.warn("Tentative de logout avec un principal non reconnu : {}", principal);
        }
    } else {
        log.warn("Aucune authentification trouvée lors de la déconnexion");
    }
}


    private TokenResponse generateTokenResponse(User user, boolean mfaRequired) {
        return generateTokenResponse(user, mfaRequired, null);
    }

    private TokenResponse generateTokenResponse(User user, boolean mfaRequired, String mfaToken) {
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        // Create new session
        Session session = Session.builder()
            .user(user)
            .refreshTokenHash(refreshToken)
            .expiresAt(LocalDateTime.now().plusDays(7))
            .build();
        sessionRepository.save(session);

        // Log authentication
        AuthAuditLog auditLog = AuthAuditLog.builder()
            .user(user)
            .eventType(mfaRequired ? "MFA_REQUIRED" : "LOGIN_SUCCESS")
            .ipAddress(RequestContextUtil.getClientIp())
            .userAgent(RequestContextUtil.getUserAgent())
            .success(true)
            .build();
        auditLogRepository.save(auditLog);

        return TokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(3600L) // 1 hour
            .mfaRequired(mfaRequired)
            .mfaToken(mfaToken)
            .build();
    }

    public void enableMfa(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setMfaEnabled(true); 
        userRepository.save(user);
        log.info("MFA enabled for user: {}", user.getEmail());
    }

    public void disableMfa(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setMfaEnabled(false);
        userRepository.save(user);
        log.info("MFA disabled for user: {}", user.getEmail());
    }
} 