package com.uds.project.service_authentification_compte.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String passwordHash;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private boolean isVerified;
    
    @Column(nullable = false)
    private boolean isActive;
    
    @Column(nullable = false)
    @Getter
    private boolean mfaEnabled;
    
    private String mfaToken;
    
    @Column(nullable = true)
    private String verificationToken;
    
    @Column(nullable = true)
    private LocalDateTime verificationTokenExpires;
    
    @Column(name = "last_login_at", nullable = true)
    private LocalDateTime lastLoginAt;

    @Column(name = "last_login_ip", nullable = true)
    private String lastLoginIp;

    @Column(name = "failed_login_attempts", nullable = true)
    @Builder.Default
    private Integer failedLoginAttempts = 0;

    @Column(name = "account_locked_until", nullable = true)
    @ColumnDefault("NULL")
    private LocalDateTime accountLockedUntil;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    @Column(name = "company_id")
    private UUID companyId;
    
    @Column(name = "agency_id")
    private UUID agencyId;
    
    @Column(columnDefinition = "text[]")
    private List<String> assignedCountries;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp with time zone")
    private LocalDateTime createdAt;

    
    @Column(name = "fcm_token")
    private String fcmToken;
    
    @Column(name = "platform")
    private String platform; // "ios" ou "android"

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .toList();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLockedUntil == null || accountLockedUntil.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive && isVerified;
    }
} 