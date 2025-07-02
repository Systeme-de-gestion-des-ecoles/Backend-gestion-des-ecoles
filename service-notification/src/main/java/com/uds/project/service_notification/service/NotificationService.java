package com.uds.project.service_notification.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uds.project.service_notification.entity.Notification;
import com.uds.project.service_notification.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    public List<Notification> getNotificationsByUserAndDate(String email, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return notificationRepository.findByDestinataireEmailAndDateCreationBetween(email, startOfDay, endOfDay);
    }

    public Notification creerEtEnvoyerNotification(String email, String sujet, String message) {
        Notification notification = new Notification();
        notification.setDestinataireEmail(email);
        notification.setSujet(sujet);
        notification.setMessage(message);
        notification.setDateCreation(LocalDateTime.now());
        notification.setEnvoye(false);

        notification = notificationRepository.save(notification);

        try {
            emailService.envoyerEmail(email, sujet, message);
            notification.setEnvoye(true);
        } catch (Exception e) {
            // Ici on log l'erreur dans EmailService, pas besoin de gérer ici
        }

        notificationRepository.save(notification);

        return notification;
    }
}
