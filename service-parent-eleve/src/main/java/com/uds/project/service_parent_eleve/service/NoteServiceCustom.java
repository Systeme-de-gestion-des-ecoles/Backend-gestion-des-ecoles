package com.uds.project.service_parent_eleve.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uds.project.service_parent_eleve.dto.NoteDto;



@FeignClient(name = "NOTE", url = "${note.url}")
public interface NoteServiceCustom {
    @GetMapping("/api/note/eleve/{id}")
   List<NoteDto> getNoteDto(@PathVariable Long id);
    
}


