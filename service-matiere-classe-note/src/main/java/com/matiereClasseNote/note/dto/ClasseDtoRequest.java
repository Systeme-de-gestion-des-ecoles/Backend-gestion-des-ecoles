package com.matiereClasseNote.note.dto;

import java.util.List;

public record ClasseDtoRequest(String code, Long nbrePlace,List<String> matieres) {

}
