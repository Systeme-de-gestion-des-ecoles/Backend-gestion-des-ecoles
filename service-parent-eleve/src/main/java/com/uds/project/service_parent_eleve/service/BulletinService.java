package com.uds.project.service_parent_eleve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uds.project.service_parent_eleve.dto.BulletinDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BulletinService {
    private final BulletinServiceCustom bulletinServiceCustom;

    public List<BulletinDto> bulletinChild(String idBulletin ){
       return bulletinServiceCustom.getBulletinDto(idBulletin);
    }
}
