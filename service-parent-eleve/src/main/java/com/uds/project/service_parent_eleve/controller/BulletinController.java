package com.uds.project.service_parent_eleve.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.dto.BulletinDto;
import com.uds.project.service_parent_eleve.service.BulletinServiceCustom;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/bulletin")
@RestController
@RequiredArgsConstructor
public class BulletinController {
    private final BulletinServiceCustom bulletinCustom;
   
    @GetMapping("/{idBulletin}")
    public List<BulletinDto> get(@PathVariable String idBulletin){
        return bulletinCustom.getBulletinDto(idBulletin);   
    }
}