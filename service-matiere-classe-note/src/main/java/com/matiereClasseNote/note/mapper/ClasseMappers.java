package com.matiereClasseNote.note.mapper;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.ClasseDtoRequest;
import com.matiereClasseNote.note.dto.ClasseDtoResponse;
import com.matiereClasseNote.note.model.Classe;
import com.matiereClasseNote.note.model.Matiere;
import com.matiereClasseNote.note.repository.MatiereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClasseMappers {
    private final MatiereRepository matiereRepository;
    public Classe DtoToEntity (ClasseDtoRequest classeDtoRequest){
        Classe classe = new  Classe();
        classe.setCode(classeDtoRequest.code());
        classe.setNbrePlace(classeDtoRequest.nbrePlace());
        classe.setMatiere(classeDtoRequest.matieres().stream().map(this::get).toList());
        return classe;
    }

    public  ClasseDtoResponse EntityToDto (Classe classe){
        return new ClasseDtoResponse(classe.getCode(),classe.getNbrePlace());

    }
    public Matiere get(String name){
        return matiereRepository.findById(name).get();
    }

}
