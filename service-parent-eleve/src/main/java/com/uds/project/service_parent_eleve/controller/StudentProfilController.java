package com.uds.project.service_parent_eleve.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.entity.StudentProfil;
import com.uds.project.service_parent_eleve.service.StudentProfilService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/profiles/student")
@RequiredArgsConstructor

public class StudentProfilController {
    private final StudentProfilService profileService;

    @PostMapping("/save")
    public void createProfile( @RequestBody StudentProfil request) {
        profileService.createProfile(request);
    }

    @GetMapping("/get/{id}")
    public StudentProfil get(@PathVariable Long id)
    {
        return profileService.getProfil(id);
    }

    @PutMapping("/update/{id}")
    public void Update(@PathVariable Long id,@RequestBody StudentProfil profil)
    {
        profileService.update(id, profil);
    }

}
