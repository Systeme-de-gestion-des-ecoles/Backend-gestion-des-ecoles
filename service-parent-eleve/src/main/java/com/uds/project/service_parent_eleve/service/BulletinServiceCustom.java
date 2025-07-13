package com.uds.project.service_parent_eleve.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.uds.project.service_parent_eleve.dto.BulletinDto;

@FeignClient(name = "BULLETIN", url = "${note.url}")
public interface BulletinServiceCustom {

    @GetMapping("/api/bulletin/all/{codeClasse}")
    List<BulletinDto> getBulletinDto(@PathVariable String codeClasse);
    
} 
