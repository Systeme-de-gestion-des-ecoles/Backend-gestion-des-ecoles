package com.uds.project.service_parent_eleve.entity;

 import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
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

     @Column(nullable = false)
    private String matricule;

     @Column(nullable = false)
    private String dateNaisLieu;
    
}
