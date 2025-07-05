package com.uds.project.service_authentification_compte.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean isActive;
    private boolean isVerified;
    private boolean mfaEnabled;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
    private RoleResponse role;
    private UUID companyId;
    private UUID agencyId;
} 