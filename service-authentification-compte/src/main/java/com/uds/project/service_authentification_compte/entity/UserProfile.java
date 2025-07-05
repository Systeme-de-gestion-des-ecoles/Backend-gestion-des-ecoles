package com.uds.project.service_authentification_compte.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfile {
    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private LocalDate dateOfBirth;
    private String nationality;
    private String passportOrIdcardNumber;
    private LocalDate passportOrIdcardExpiryDate;
    private String specialNeeds;
    private Boolean isChild;
    private Boolean isInfant;
    private String mealPreference;
    
}
