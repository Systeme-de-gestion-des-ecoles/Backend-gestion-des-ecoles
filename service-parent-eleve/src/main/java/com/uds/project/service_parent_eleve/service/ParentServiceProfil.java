package com.uds.project.service_parent_eleve.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import com.uds.project.service_parent_eleve.entity.ParentProfil;
import com.uds.project.service_parent_eleve.repository.ParentProfilRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@Service
@RequiredArgsConstructor

public class ParentServiceProfil {
        private final ParentProfilRepository profileRepository;
    
    public void createProfile(ParentProfil request) {
        profileRepository.save(request);   
    }

    public ParentProfil getProfil(Long id){
        return profileRepository.findById(id).get();
    }

    public void Delete(Long id)
    {
        profileRepository.delete(profileRepository.findById(id).get());
    }

    public void update(Long id,ParentProfil profil)
    {
        ParentProfil profil3 = profileRepository.findById(id).get();

        profil3.setEmail(profil.getEmail());
        profil3.setNom(profil.getNom());
        profil3.setPhone(profil.getPhone());
        
        profileRepository.save(profil3);
    }

}