package com.uds.project.service_parent_eleve.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId; // ID de l'utilisateur dans le service d'authentification
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

     @Column(nullable = false)
    private String adresse;
    
    @Column(nullable = false)
    private String motDePasse;

    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;

}



