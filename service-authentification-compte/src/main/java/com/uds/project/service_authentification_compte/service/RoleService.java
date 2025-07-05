package com.uds.project.service_authentification_compte.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uds.project.service_authentification_compte.dto.PermissionResponse;
import com.uds.project.service_authentification_compte.dto.RoleRequest;
import com.uds.project.service_authentification_compte.dto.RoleResponse;
import com.uds.project.service_authentification_compte.entity.Permission;
import com.uds.project.service_authentification_compte.entity.Role;
import com.uds.project.service_authentification_compte.repository.PermissionRepository;
import com.uds.project.service_authentification_compte.repository.RoleRepository;
import com.uds.project.service_authentification_compte.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public RoleResponse getRoleById(UUID id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return mapToResponse(role);
    }

    @Transactional
    public RoleResponse createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        
        if (request.getPermissionIds() != null) {
            Set<Permission> permissions = request.getPermissionIds().stream()
                .map(permissionId -> permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Permission not found")))
                .collect(Collectors.toSet());
            role.setPermissions(permissions);
        }

        return mapToResponse(roleRepository.save(role));
    }

    @Transactional
    public RoleResponse updateRole(UUID id, RoleRequest request) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        role.setName(request.getName());
        role.setDescription(request.getDescription());

        if (request.getPermissionIds() != null) {
            Set<Permission> permissions = request.getPermissionIds().stream()
                .map(permissionId -> permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Permission not found")))
                .collect(Collectors.toSet());
            role.setPermissions(permissions);
        }

        return mapToResponse(roleRepository.save(role));
    }

    @Transactional
    public void deleteRole(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }

    @Transactional
    public RoleResponse addPermissionToRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        role.getPermissions().add(permission);
        return mapToResponse(roleRepository.save(role));
    }

    @Transactional
    public RoleResponse removePermissionFromRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        role.getPermissions().remove(permission);
        return mapToResponse(roleRepository.save(role));
    }

    @Transactional
    public List<RoleResponse> initializeRoles() {
        List<Role> roles = new ArrayList<>();
        
        // Créer le rôle Admin
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_ADMIN");
                role.setDescription("Super Administrator role with full access");
                return roleRepository.save(role);
            });
        roles.add(adminRole);

        // Créer le rôle Promoteur
        Role promoterRole = roleRepository.findByName("ROLE_PROMOTER")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_PROMOTER");
                role.setDescription("Country promoter role for managing companies");
                return roleRepository.save(role);
            });
        roles.add(promoterRole);

        // Créer le rôle Company Manager
        Role companyManagerRole = roleRepository.findByName("ROLE_COMPANY_MANAGER")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_COMPANY_MANAGER");
                role.setDescription("Company manager role for managing agencies");
                return roleRepository.save(role);
            });
        roles.add(companyManagerRole);

        // Créer le rôle Agency Manager
        Role agencyManagerRole = roleRepository.findByName("ROLE_AGENCY_MANAGER")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_AGENCY_MANAGER");
                role.setDescription("Agency manager role for managing bookings");
                return roleRepository.save(role);
            });
        roles.add(agencyManagerRole);

        // Créer le rôle User
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_USER");
                role.setDescription("Basic user role");
                return roleRepository.save(role);
            });
        roles.add(userRole);

        // Associer les permissions aux rôles
        associatePermissionsToRoles();

        return roles.stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private void associatePermissionsToRoles() {
        // Récupérer toutes les permissions
        List<Permission> allPermissions = permissionRepository.findAll();
        Map<String, Permission> permissionMap = allPermissions.stream()
            .collect(Collectors.toMap(Permission::getName, p -> p));

        // Associer les permissions au rôle Admin
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
        adminRole.setPermissions(new HashSet<>(allPermissions));
        roleRepository.save(adminRole);

        // Associer les permissions au rôle Promoteur
        Role promoterRole = roleRepository.findByName("ROLE_PROMOTER").orElseThrow();
        Set<Permission> promoterPermissions = new HashSet<>();
        promoterPermissions.add(permissionMap.get("COUNTRY_MANAGE"));
        promoterPermissions.add(permissionMap.get("COMPANY_APPROVE"));
        promoterPermissions.add(permissionMap.get("COMPANY_REJECT"));
        promoterPermissions.add(permissionMap.get("COMPANY_VIEW_ALL"));
        // Permissions pour les villes
        promoterPermissions.add(permissionMap.get("CITY_CREATE"));
        promoterPermissions.add(permissionMap.get("CITY_UPDATE"));
        promoterPermissions.add(permissionMap.get("CITY_DELETE"));
        promoterPermissions.add(permissionMap.get("CITY_VIEW"));
        promoterRole.setPermissions(promoterPermissions);
        roleRepository.save(promoterRole);

        // Associer les permissions au rôle Company Manager
        Role companyManagerRole = roleRepository.findByName("ROLE_COMPANY_MANAGER").orElseThrow();
        Set<Permission> companyManagerPermissions = new HashSet<>();
        companyManagerPermissions.add(permissionMap.get("COMPANY_MANAGE"));
        companyManagerPermissions.add(permissionMap.get("AGENCY_CREATE"));
        companyManagerPermissions.add(permissionMap.get("AGENCY_UPDATE"));
        companyManagerPermissions.add(permissionMap.get("AGENCY_DELETE"));
        companyManagerPermissions.add(permissionMap.get("AGENCY_VIEW_ALL"));
        companyManagerPermissions.add(permissionMap.get("USER_MANAGE"));
        companyManagerRole.setPermissions(companyManagerPermissions);
        roleRepository.save(companyManagerRole);

        // Associer les permissions au rôle Agency Manager
        Role agencyManagerRole = roleRepository.findByName("ROLE_AGENCY_MANAGER").orElseThrow();
        Set<Permission> agencyManagerPermissions = new HashSet<>();
        agencyManagerPermissions.add(permissionMap.get("AGENCY_MANAGE"));
        agencyManagerPermissions.add(permissionMap.get("USER_MANAGE_AGENCY"));
        agencyManagerPermissions.add(permissionMap.get("BOOKING_MANAGE"));
        agencyManagerRole.setPermissions(agencyManagerPermissions);
        roleRepository.save(agencyManagerRole);

        // Associer les permissions au rôle User
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow();
        Set<Permission> userPermissions = new HashSet<>();
        userPermissions.add(permissionMap.get("USER_PROFILE_VIEW"));
        userPermissions.add(permissionMap.get("USER_PROFILE_UPDATE"));
        userPermissions.add(permissionMap.get("BOOKING_CREATE"));
        userPermissions.add(permissionMap.get("BOOKING_VIEW"));
        userRole.setPermissions(userPermissions);
        roleRepository.save(userRole);
    }

    private RoleResponse mapToResponse(Role role) {
        return RoleResponse.builder()
            .id(role.getId())
            .name(role.getName())
            .description(role.getDescription())
            .permissions(role.getPermissions().stream()
                .map(permission -> PermissionResponse.builder()
                    .id(permission.getId())
                    .name(permission.getName())
                    .description(permission.getDescription())
                    .action(permission.getAction())
                    .resource(permission.getResource())
                    .build())
                .collect(Collectors.toSet()))
            .build();
    }
} 