package com.matiereClasseNote.note.model;

public record Student(Long id,
                        String nom,
                        String prenom,
                        String adresse,
                        String motDePasse,
                        String matricule,
                        String dateNaisLieu,
                        String code) {

}
