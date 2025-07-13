package com.uds.project.service_parent_eleve.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uds.project.service_parent_eleve.dto.AbscenceDto;



@FeignClient(name = "ABSCENCE", url = "${custom.url}")
public interface AbscenceServiceCustom {
    @GetMapping("/api/v1/absences/{id}") 
    AbscenceDto getAbscenceDto(@PathVariable Long id);
    
}
