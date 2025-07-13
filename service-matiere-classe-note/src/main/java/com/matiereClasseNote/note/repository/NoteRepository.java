package com.matiereClasseNote.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matiereClasseNote.note.model.Matiere;
import com.matiereClasseNote.note.model.Note;

public interface NoteRepository extends JpaRepository<Note,Long>{

    List<Note> findByMatiere(Matiere matiere);
    List<Note> findByIdEleve(String idEleve);
}
