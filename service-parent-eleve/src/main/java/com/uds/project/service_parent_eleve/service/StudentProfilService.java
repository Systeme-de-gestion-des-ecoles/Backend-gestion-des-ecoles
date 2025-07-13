package com.uds.project.service_parent_eleve.service;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.uds.project.service_parent_eleve.entity.StudentProfil;
import com.uds.project.service_parent_eleve.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@Service
@RequiredArgsConstructor
public class StudentProfilService {

    private final StudentProfileRepository profileRepository;
    
    public void createProfile(StudentProfil request) {
        profileRepository.save(request);   
    }

    public StudentProfil getProfil(Long id){
        return profileRepository.findById(id).get();
    }

    public void Delete(Long id)
    {
        profileRepository.delete(profileRepository.findById(id).get());
    }

    public void update(Long id,StudentProfil profil)
    {
        StudentProfil profil2 = profileRepository.findById(id).get();
        profil2.setNom(profil.getNom());
        profil2.setPrenom(profil.getPrenom());
        profileRepository.save(profil2);
    }
}
