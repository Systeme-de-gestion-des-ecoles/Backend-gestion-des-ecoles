package com.uds.project.service_parent_eleve.dto;

import java.time.LocalDateTime;

public record AbscenceDto(
    Long id,
    Long studentId,
    String reason,
    String status,
    LocalDateTime date,
    LocalDateTime updateDate
) {
}