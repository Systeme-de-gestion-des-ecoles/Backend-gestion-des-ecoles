package com.uds.project.service_parent_eleve.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.dto.NoteDto;
import com.uds.project.service_parent_eleve.service.NoteService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/note")
@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
   
    @GetMapping("/{id}")
    public List<NoteDto> get(@PathVariable Long id){
       
        return noteService.noteChild(id);
        
    }

}
