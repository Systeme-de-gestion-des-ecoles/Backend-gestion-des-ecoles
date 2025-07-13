package com.matiereClasseNote.note.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.matiereClasseNote.note.dto.BulletinDtoResponse;
import com.matiereClasseNote.note.dto.NoteBulletinDtoResponse;
import com.matiereClasseNote.note.dto.NoteDtoResponse;
import com.matiereClasseNote.note.mapper.BulletinMappers;
import com.matiereClasseNote.note.model.Bulletin;
import com.matiereClasseNote.note.model.Note;
import com.matiereClasseNote.note.model.Student;
import com.matiereClasseNote.note.repository.BulletinRepository;
import com.matiereClasseNote.note.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BulletinService {
    private final NoteServiceImplement noteServiceImplement;
    private final BulletinRepository bulletinRepository;
    private final BulletinMappers bulletinMappers;
    private final StudentServiceCustum studentServiceCustum;
    private final NoteRepository noteRepository;
    public Double Moyenne(List<NoteDtoResponse> notes)//calcul la moyenne d'un eleve
    {
        Double moyenne = 0.0;
        float totalCoef = 0;
        for (NoteDtoResponse note : notes) {
            if(note != null)
            {
                Note note2 = noteRepository.findById(note.idNote()).orElseThrow(()->new RuntimeException("pas de note"));
                moyenne += note2.getNote() * note2.getMatiere().getCoeff();
                totalCoef += note2.getMatiere().getCoeff();
            }
        }

       if(moyenne <= 0)
       {
          return 0.0;
       }
        moyenne = moyenne/totalCoef;
        return moyenne;
    }

    public void save(String code)
    {
        List<Student> students = studentServiceCustum.getStudentClass(code);
        List<Double> moyen = new ArrayList<>();
        Double moyenGeneral = 0.0;
        for (Student student : students) {
            List<NoteDtoResponse> notes = noteServiceImplement.getNoteForEleve(String.valueOf(student.id()));//retourne la liste des notes de eleve
            moyen.add(Moyenne(notes));
            moyenGeneral += Moyenne(notes);
        }
        int taille = students.size();
        moyenGeneral = moyenGeneral / students.size();

        List<Double> trierMoyenne = moyen.stream().sorted(Comparator.reverseOrder()).toList();//tri des moyennes par ordre croissant

        for(int i = 0; i < taille;i++)
        {
            Bulletin bulletin = new Bulletin(null,
                                            students.get(i).matricule(),
                                            students.get(i).id(),
                                            code,
                                            noteRepository.findByIdEleve(String.valueOf(students.get(i).id())),
                                            moyen.get(i),
                                            moyenGeneral,                                 
                                            (long)trierMoyenne.indexOf(moyen.get(i)) + 1,
                                            (long)1,
                                            Appreciation(moyen.get(i))
                                            );
                                           

            bulletinRepository.save(bulletin);//sauvegarder en base de donnee
        }


      }

/*     public void update(String code, Long idBulletin){
      Classe classe = classeRepository.findById(code).orElseThrow( () -> new RuntimeException("La classe n'existe pas"));
      Bulletin bulletin = bulletinRepository.findBy(idBulletin).get();
      Bulletin bulletin = bulletinRepository.

    } */

    public List<BulletinDtoResponse> bulletins(String code){
        return bulletinRepository.findByCode(code).stream().map(bulletinMappers::EntityToDto).toList();
    }

    public BulletinDtoResponse bulletinStudent(String matricule)
    {
        return bulletinMappers.EntityToDto(bulletinRepository.findByMatricule(matricule));
    }

    public String Appreciation(Double note){
        
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

    public List<NoteBulletinDtoResponse> getAll() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    public List<Student> getNoteForEleve(String id) {
      return studentServiceCustum.getStudentClass(id);
    } 
}
