package com.matiereClasseNote.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.ClasseDtoRequest;
import com.matiereClasseNote.note.dto.ClasseDtoResponse;
import com.matiereClasseNote.note.mapper.ClasseMappers;
import com.matiereClasseNote.note.model.Classe;
import com.matiereClasseNote.note.repository.ClasseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClasseService {
    private final ClasseMappers classeMappers;
    private final ClasseRepository classeRepository;

    public void save(ClasseDtoRequest classeDtoRequest){
        Classe classe = new Classe();
        classe = classeMappers.DtoToEntity(classeDtoRequest);

        classeRepository.save(classe);
    }

    public void update( ClasseDtoRequest classeDtoRequest, String code){
        if (code == null || classeDtoRequest == null) {
            throw new RuntimeException("Pas de code");
        }

        Classe classe = new Classe();
        classe = classeMappers.DtoToEntity(classeDtoRequest);
        classe.setCode(code);

        classeRepository.save(classe);
    }

    public List<ClasseDtoResponse> getAll(){
    return classeRepository.findAll().stream().map(classeMappers::EntityToDto).toList();
    }
}