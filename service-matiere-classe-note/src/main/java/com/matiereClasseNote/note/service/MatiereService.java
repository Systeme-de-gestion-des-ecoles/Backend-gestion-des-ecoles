package com.matiereClasseNote.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.MatiereDtoRequest;
import com.matiereClasseNote.note.dto.MatiereDtoResponse;
import com.matiereClasseNote.note.mapper.MatiereMappers;
import com.matiereClasseNote.note.model.Matiere;
import com.matiereClasseNote.note.repository.MatiereRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatiereService {
    private final MatiereMappers mappers;
    private final MatiereRepository matiereRepository;
    public void save( MatiereDtoRequest matiereDtoRequest )
    {
        Matiere matiere = mappers.DtoToEntity(matiereDtoRequest);

        matiereRepository.save(matiere);
    }

    public void delete ( String matiereDtoRequest){
        if (matiereDtoRequest == null) {
            throw new RuntimeException("Pas de nom");
        }

        Matiere matiere = matiereRepository.findById(matiereDtoRequest).get();
        
        matiereRepository.delete(matiere);
    }

    public void update(String nom, MatiereDtoRequest matiereDtoRequest ){

        Matiere matiere = matiereRepository.findById(nom).get();

        matiere.setCoeff(matiereDtoRequest.coeff());
       
        matiereRepository.save(matiere);
        
    }

    public MatiereDtoResponse getNomForMatiere(String name){
        return mappers.EntityToDto(matiereRepository.findById(name).get());
    }
   
    public List<MatiereDtoResponse> getAll(){
        return matiereRepository.findAll().stream().map(mappers::EntityToDto).toList();
    }
}
