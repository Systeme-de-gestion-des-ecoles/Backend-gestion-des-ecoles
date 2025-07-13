package com.matiereClasseNote.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matiereClasseNote.note.model.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere,String> {
    
     List<Matiere> findByNom(String nom);

}


