package com.uds.project.service_authentification_compte.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionResponse {
    private UUID id;
    private String name;
    private String description;
    private String action;
    private String resource;
} 