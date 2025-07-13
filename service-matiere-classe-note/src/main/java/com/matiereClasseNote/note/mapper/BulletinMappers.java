package com.matiereClasseNote.note.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.BulletinDtoResponse;
import com.matiereClasseNote.note.dto.NoteBulletinDtoResponse;
import com.matiereClasseNote.note.model.Bulletin;
import com.matiereClasseNote.note.model.Note;
import com.matiereClasseNote.note.service.StudentServiceCustum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BulletinMappers {
     private final StudentServiceCustum studentServiceCustum;
    public NoteBulletinDtoResponse toDto(Note note)
    {
        String appreciation = Aprreciation(note.getNote());
       return new NoteBulletinDtoResponse(note.getMatiere().getNom(),
                                          note.getMatiere().getCoeff(),
                                          note.getNote(),
                                          note.getNote() * note.getMatiere().getCoeff(),
                                          appreciation
                                          );
    }

    public BulletinDtoResponse EntityToDto(Bulletin bulletin){
        List<NoteBulletinDtoResponse> noteBulletinDtoResponses = bulletin.getNotes().stream().
                                                                map(this::toDto).toList();

        return new BulletinDtoResponse(bulletin.getIdBulletin(),
                                       bulletin.getMatricule(),
                                       studentServiceCustum.getStudent(bulletin.getIdStudent()).nom(),
                                       bulletin.getCode(),
                                       noteBulletinDtoResponses,
                                       bulletin.getMoyEleve(),
                                       bulletin.getMoyGenerale(),
                                       bulletin.getRang(),
                                       bulletin.getNombreAbsence(),
                                       bulletin.getAppreciation());
    }

    public String Aprreciation(float note){
        
       if (note < 8){
        return "Faible";
       }
      else if(note >= 8 && note < 9 ){
        return "Insuffisant";
      }
      else if(note >= 9 && note < 10){
        return "Mediocre";
      }
      else if (note >= 10 && note < 12 ){
         return "Passable";
      }
      else if (note >= 12 && note < 14){
        return "Assez bien";
      }
      else if(note >= 14 && note < 17){
        return "Bien";
      }
      else if(note >= 17 && note <19){
        return "Tres bien";
      }
      else if(note >= 19 && note <= 20){
        return "Excellent";
      }
      else return null;
     
    

    } 
}
