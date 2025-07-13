package com.uds.project.service_parent_eleve.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="parent_profils")
@AllArgsConstructor
@NoArgsConstructor
public class ParentProfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
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
