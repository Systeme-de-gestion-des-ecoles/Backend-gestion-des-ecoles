package com.uds.project.service_notification.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.project.service_notification.entity.Notification;

//MongoRepository est une interface fournie par Spring Data MongoDB pour faire des operation tel que findById() , save()...
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByDestinataireEmailAndDateCreationBetween(String email, LocalDateTime start, LocalDateTime end);
}
