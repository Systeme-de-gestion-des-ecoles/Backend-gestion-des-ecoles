package com.uds.project.service_authentification_compte.security;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.uds.project.service_authentification_compte.entity.User;
import com.uds.project.service_authentification_compte.exceptions.AuthenticationException;

@Component
public class MfaTokenProvider {

    private final Map<String, MfaData> mfaTokens = new ConcurrentHashMap<>();
    private final Map<UUID, String> mfaCodes = new ConcurrentHashMap<>();

    public String generateMfaToken(User user) {
        String token = UUID.randomUUID().toString();
        mfaTokens.put(token, new MfaData(user, LocalDateTime.now().plusMinutes(5)));
        return token;
    }

    public String generateMfaCode() {
        String code = String.format("%06d", (int) (Math.random() * 1000000));
        return code;
    }

    public User validateMfaToken(String token) {
        MfaData mfaData = mfaTokens.get(token);
        if (mfaData == null || mfaData.expiresAt.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("Invalid or expired MFA token");
        }
        return mfaData.user;
    }

    public boolean validateMfaCode(User user, String code) {
        String storedCode = mfaCodes.get(user.getId());
        if (storedCode == null || !storedCode.equals(code)) {
            return false;
        }
        mfaCodes.remove(user.getId());
        return true;
    }

    private static class MfaData {
        final User user;
        final LocalDateTime expiresAt;

        MfaData(User user, LocalDateTime expiresAt) {
            this.user = user;
            this.expiresAt = expiresAt;
        }
    }
} 