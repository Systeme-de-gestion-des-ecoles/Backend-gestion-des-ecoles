package com.matiereClasseNote.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matiereClasseNote.note.model.Bulletin;

public interface BulletinRepository extends JpaRepository<Bulletin,Long> {
    List<Bulletin> findByCode(String code);
    Bulletin findByMatricule(String matricule);
}
