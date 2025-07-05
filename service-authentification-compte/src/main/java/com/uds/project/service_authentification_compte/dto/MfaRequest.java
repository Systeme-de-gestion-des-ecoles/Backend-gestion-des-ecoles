package com.uds.project.service_authentification_compte.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MfaRequest {
    @NotBlank(message = "MFA token is required")
    private String mfaToken;
    
    @NotBlank(message = "MFA code is required")
    private String mfaCode;
} 