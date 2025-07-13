package com.matiereClasseNote.note.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiereClasseNote.note.dto.BulletinDtoResponse;
import com.matiereClasseNote.note.model.Student;
import com.matiereClasseNote.note.service.BulletinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/bulletin")
@RequiredArgsConstructor
public class BulletinContoller {
    private final BulletinService bulletinService;
   
    @GetMapping("/save/{code}")
    public void save(@PathVariable String code){
        bulletinService.save(code);
    }
    
    @GetMapping("/all/{code}")
    public List<BulletinDtoResponse> getAll(@PathVariable String code){
        return bulletinService.bulletins(code);
    }

    @GetMapping("/student/class/{id}")
    public List<Student> getStudent(@PathVariable String id)
    {
        return bulletinService.getNoteForEleve(id);
    }
}
