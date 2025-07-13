package com.matiereClasseNote.note.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.NoteDtoRequest;
import com.matiereClasseNote.note.dto.NoteDtoResponse;
import com.matiereClasseNote.note.model.Matiere;
import com.matiereClasseNote.note.model.Note;
import com.matiereClasseNote.note.mapper.NoteMappers;
import com.matiereClasseNote.note.repository.MatiereRepository;
import com.matiereClasseNote.note.repository.NoteRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor

public class NoteServiceImplement implements NoteService {

    private final NoteRepository noteRepository;
    private final MatiereRepository matiereRepository;
    private final NoteMappers noteMappers;
    @Override
    public void save( NoteDtoRequest noteDtoRequest) {
        if (noteDtoRequest == null) {
            return;
        }
        Note note = noteMappers.DtoToEntity(noteDtoRequest);
        noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
          return;  
        }

        Note note = noteRepository.findById(id).get();
        if (note == null) {
            return;
            
        }
        noteRepository.delete(note);
    }

    @Override
    public void update(Long id, NoteDtoRequest noteDtoRequest) {
        if (id == null || noteDtoRequest == null) {
          return;  
        }

        Note note = noteRepository.findById(id).get();
        if (note == null) {
            return;
        }

        note.setNote(noteDtoRequest.note());
        noteRepository.save(note);
    }

    @Override
    public List<NoteDtoResponse> getNoteForMatiere(String idMatiere) {
        if (idMatiere == null) {
          return null;  
        }
        Matiere matiere = matiereRepository.findById(idMatiere).get();
        return noteRepository.findByMatiere(matiere).stream().map(noteMappers::EntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<NoteDtoResponse> getNoteForEleve(String idEleve) {
        if (idEleve == null) {
          return null;  
        }   
        return noteRepository.findByIdEleve(idEleve).stream().map(noteMappers::EntityToDto).collect(Collectors.toList());
    }

}
