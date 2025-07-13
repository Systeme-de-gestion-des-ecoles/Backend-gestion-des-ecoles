package com.uds.project.service_authentification_compte.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uds.project.service_authentification_compte.dto.PermissionResponse;
import com.uds.project.service_authentification_compte.dto.RoleResponse;
import com.uds.project.service_authentification_compte.dto.SuperAdminInitRequest;
import com.uds.project.service_authentification_compte.dto.UserResponse;
import com.uds.project.service_authentification_compte.entity.Role;
import com.uds.project.service_authentification_compte.entity.User;
import com.uds.project.service_authentification_compte.repository.RoleRepository;
import com.uds.project.service_authentification_compte.repository.UserRepository;
import com.uds.project.service_authentification_compte.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToResponse(user);
    }

    @Transactional
    public UserResponse activateUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(true);
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse deactivateUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(false);
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse verifyUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setVerified(true);
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse unverifyUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setVerified(false);
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse lockUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(false);
        user.setAccountLockedUntil(LocalDateTime.now().plusDays(1));
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse unlockUser(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(true);
        user.setAccountLockedUntil(null);
        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    public User initializeSuperAdmin(SuperAdminInitRequest request) {
        // Vérifier si un super admin existe déjà
        if (userRepository.existsByRoleName("ROLE_ADMIN")) {
            throw new IllegalStateException("Super admin already exists");
        }

        // Vérification supprimée car l’email est supprimé du modèle
        // if (userRepository.existsByEmail(request.getEmail())) {
        //     throw new IllegalArgumentException("Email already in use");
        // }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_ADMIN");
                role.setDescription("Super Administrator role with full access");
                return roleRepository.save(role);
            });

        User superAdmin = User.builder()
            // .email(request.getEmail()) // ⛔ Email supprimé du modèle User
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            // .phone(request.getPhone()) // ⛔ Téléphone supprimé
            .isVerified(true)
            .isActive(true)
            .mfaEnabled(false)
            .role(adminRole)
            .build();

        return userRepository.save(superAdmin);
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            // .email(user.getEmail())        // ⛔ Supprimé du modèle
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            // .phone(user.getPhone())        // ⛔ Supprimé
            .isVerified(user.isVerified())
            .isActive(user.isActive())
            .mfaEnabled(user.isMfaEnabled())
            .role(mapToRoleResponse(user.getRole()))
            // .companyId(user.getCompanyId())  // ⛔ Supprimé
            // .agencyId(user.getAgencyId())    // ⛔ Supprimé
            .build();
    }

    private RoleResponse mapToRoleResponse(Role role) {
        return RoleResponse.builder()
            .id(role.getId())
            .name(role.getName())
            .description(role.getDescription())
            .permissions(role.getPermissions().stream()
                .map(permission -> PermissionResponse.builder()
                    .id(permission.getId())
                    .name(permission.getName())
                    .description(permission.getDescription())
                    .resource(permission.getResource())
                    .action(permission.getAction())
                    .build())
                .collect(Collectors.toSet()))
            .build();
    }
}
