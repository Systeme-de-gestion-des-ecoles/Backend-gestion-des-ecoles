package com.uds.project.service_notification.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.uds.project.service_notification.entity.Notification;
import com.uds.project.service_notification.entity.NotificationDto;
import com.uds.project.service_notification.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification creerNotification(@RequestBody NotificationDto dto) {
        return notificationService.creerEtEnvoyerNotification(dto.getEmail(), dto.getSujet(), dto.getMessage());
    }

    @GetMapping
    public List<Notification> getNotificationsByUserAndDate(
            @RequestParam String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return notificationService.getNotificationsByUserAndDate(email, date);
    }
}
