package com.uds.project.service_parent_eleve.dto;

import java.util.List;

public record BulletinDto(
                                Long idBulletin,
                                  String matricule,
                                  String nom, 
                                  String code,
                                  List<NoteBulletinDtoResponse> notes,
                                  Double moyEleve,
                                  Double moyGenerale,
                                  Long rang,
                                  Long absence,
                                  String appreciation)
 {

}
