package com.uds.project.service_parent_eleve.service;

import org.springframework.stereotype.Service;

import com.uds.project.service_parent_eleve.dto.AbscenceDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbscenceService {
    
    private final AbscenceServiceCustom abscenceServiceCustom;

    public AbscenceDto abscenceChild(Long id ){
        AbscenceDto abscenceDto = abscenceServiceCustom.getAbscenceDto(id);
        return abscenceDto;
    }

}
