package com.uds.project.service_parent_eleve.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.entity.ParentProfil;
import com.uds.project.service_parent_eleve.service.ParentServiceProfil;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/profiles/parent")
@RequiredArgsConstructor

public class ParentProfilController {

     private final ParentServiceProfil profileService;

    @PostMapping
    public void createProfile( @RequestBody ParentProfil request) {
        profileService.createProfile(request);
    }

    @GetMapping("/get/{id}")
    public ParentProfil get(@PathVariable Long id)
    {
        return profileService.getProfil(id);
    }

    @PutMapping("/update/{id}")
    public void Update(@PathVariable Long id,@RequestBody ParentProfil profil)
    {
        profileService.update(id, profil);
    }

}
