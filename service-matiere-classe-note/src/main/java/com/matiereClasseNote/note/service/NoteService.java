package com.matiereClasseNote.note.service;

import java.util.List;

import com.matiereClasseNote.note.dto.NoteDtoRequest;
import com.matiereClasseNote.note.dto.NoteDtoResponse;

public interface NoteService {

    public void save(NoteDtoRequest noteDtoRequest);  
    public void delete(Long id);
    public void update(Long id , NoteDtoRequest noteDtoRequest);
    public List<NoteDtoResponse> getNoteForMatiere(String idMatiere);
    public List<NoteDtoResponse> getNoteForEleve(String idEleve);

    
} 
