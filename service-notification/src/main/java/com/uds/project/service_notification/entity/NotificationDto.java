package com.uds.project.service_notification.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private String email;
    private String sujet;
    private String message;

}

