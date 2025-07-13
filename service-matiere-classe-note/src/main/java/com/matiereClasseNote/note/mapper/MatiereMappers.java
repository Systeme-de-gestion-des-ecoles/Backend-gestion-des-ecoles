package com.matiereClasseNote.note.mapper;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.MatiereDtoRequest;
import com.matiereClasseNote.note.dto.MatiereDtoResponse;
import com.matiereClasseNote.note.model.Matiere;
@ Service
public class MatiereMappers {

    public Matiere DtoToEntity (MatiereDtoRequest matiereDtoRequest){
        Matiere matiere = new Matiere ();
        matiere.setNom(matiereDtoRequest.nom());
        matiere.setCoeff(matiereDtoRequest.coeff());
       
        return matiere;
    }
    public MatiereDtoResponse EntityToDto (Matiere matiere){
        return new MatiereDtoResponse(matiere.getNom(),matiere.getCoeff());
    }
}
