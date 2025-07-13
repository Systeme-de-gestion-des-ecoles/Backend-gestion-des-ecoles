package com.matiereClasseNote.note.mapper;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.NoteDtoRequest;
import com.matiereClasseNote.note.dto.NoteDtoResponse;
import com.matiereClasseNote.note.model.Note;
import com.matiereClasseNote.note.repository.MatiereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteMappers {
    private final MatiereRepository matiereRepository;
    public Note DtoToEntity(NoteDtoRequest noteDtoRequest){
        Note note= new Note(); 
        note.setNote(noteDtoRequest.note());
        note.setMatiere(matiereRepository.findById(noteDtoRequest.idMatiere()).orElseThrow( ()-> new RuntimeException("pas de matiere")));
        note.setIdEleve(noteDtoRequest.idEleve());
        note.setIdEnseignant(noteDtoRequest.idEnseignant());
        return note;
    }

    public NoteDtoResponse EntityToDto(Note note){

        return new NoteDtoResponse(note.getIdNote(),note.getNote());

    }
}
