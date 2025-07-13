package com.matiereClasseNote.note.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

public class Classe {
    @Id
    @Column(unique = true)
    private String code;
    private Long nbrePlace;
    @ManyToMany
    @JoinTable(name = "Classe_Matiere",joinColumns =@JoinColumn(name = "idMatiere"),inverseJoinColumns = @JoinColumn(name = "idClasse"))
    private List<Matiere> matiere;
}
