package com.matiereClasseNote.note.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiereClasseNote.note.dto.ClasseDtoRequest;
import com.matiereClasseNote.note.dto.ClasseDtoResponse;
import com.matiereClasseNote.note.service.ClasseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/classe")
@RequiredArgsConstructor
public class ClasseController {
    private final ClasseService classeService;

    @PostMapping("/save")
    public void save(@RequestBody  ClasseDtoRequest classeDtoRequest){

        classeService.save(classeDtoRequest);
    }

    @PutMapping("/update/{code}")
    public void update(@RequestBody ClasseDtoRequest classeDtoRequest, @PathVariable String code ){

        classeService.update(classeDtoRequest, code);
    }
    @GetMapping("/all")
    public List<ClasseDtoResponse> getAll(){
        return classeService.getAll();
    }

}
