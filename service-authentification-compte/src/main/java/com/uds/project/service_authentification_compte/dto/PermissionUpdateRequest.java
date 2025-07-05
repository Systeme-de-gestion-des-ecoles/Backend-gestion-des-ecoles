package com.uds.project.service_authentification_compte.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionUpdateRequest {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Description is required")
    private String description;
} 