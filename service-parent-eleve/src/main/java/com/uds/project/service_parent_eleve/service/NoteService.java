package com.uds.project.service_parent_eleve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uds.project.service_parent_eleve.dto.NoteDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteServiceCustom noteServiceCustom;

    public List<NoteDto> noteChild(Long id){
       return  noteServiceCustom.getNoteDto(id); 
    }


}
