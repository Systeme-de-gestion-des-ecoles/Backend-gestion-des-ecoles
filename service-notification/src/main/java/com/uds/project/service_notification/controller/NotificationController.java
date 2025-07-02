package com.uds.project.service_notification.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_notification.entity.Notification;
import com.uds.project.service_notification.entity.NotificationDto;
import com.uds.project.service_notification.service.NotificationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint existant pour créer une notification
    @PostMapping
    public Notification creerNotification(@RequestBody NotificationDto dto) {
        return notificationService.creerEtEnvoyerNotification(dto.getEmail(), dto.getSujet(), dto.getMessage());
    }

    // Nouveau endpoint pour récupérer les notifications par utilisateur et date
    @GetMapping
    public List<Notification> getNotificationsByUserAndDate(@RequestParam String email,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return notificationService.getNotificationsByUserAndDate(email, date);
    }
}
