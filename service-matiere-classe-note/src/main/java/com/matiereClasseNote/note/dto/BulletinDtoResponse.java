package com.matiereClasseNote.note.dto;

import java.util.List;

public record BulletinDtoResponse(Long idBulletin,
                                  String matricule,
                                  String nom, 
                                  String code,
                                  List<NoteBulletinDtoResponse> notes,
                                  Double moyEleve,
                                  Double moyGenerale,
                                  Long rang,
                                  Long absence,
                                  String appreciation) {

}
