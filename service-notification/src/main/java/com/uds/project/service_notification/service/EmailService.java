package com.uds.project.service_notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void envoyerEmail(String to, String sujet, String texte) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("wakamyann19@gmail.com"); // Mets ici ton adresse mail configurée
            message.setTo(to);
            message.setSubject(sujet);
            message.setText(texte);

            logger.info("Envoi d'email à {}", to);
            mailSender.send(message);
            logger.info("Email envoyé avec succès à {}", to);
        } catch (MailException e) {
            logger.error("Erreur lors de l'envoi de l'email à {}: {}", to, e.getMessage());
        }
    }
}

