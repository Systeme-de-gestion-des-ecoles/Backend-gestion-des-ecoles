package com.matiereClasseNote.note.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBulletin;
    private String matricule;
    private Long idStudent;
    private String code; 
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "idNote"),inverseJoinColumns = @JoinColumn(name = "idBulletin"))
    private List<Note> notes;
    private Double moyEleve;
    private Double moyGenerale;
    @Column(nullable = true)
    private Long rang;
    private Long nombreAbsence;
    private String appreciation;


}
