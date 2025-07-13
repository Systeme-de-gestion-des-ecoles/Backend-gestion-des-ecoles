package com.uds.project.service_parent_eleve.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.dto.AbscenceDto;
import com.uds.project.service_parent_eleve.service.AbscenceServiceCustom;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/abscence")
@RestController
@RequiredArgsConstructor
public class AbscenceController {
    private final AbscenceServiceCustom abServiceCustom;
   
    @GetMapping("/{idAbscence}")
    public AbscenceDto get(@PathVariable Long idAbscence){
       
        return abServiceCustom.getAbscenceDto(idAbscence);
        
    }

    
}


