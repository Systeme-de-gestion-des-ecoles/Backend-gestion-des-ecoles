package com.uds.project.service_authentification_compte.dto;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {
    @NotBlank(message = "Le nom du rôle est requis")
    private String name;
    
    private String description;
    
    private Set<UUID> permissionIds;
} 