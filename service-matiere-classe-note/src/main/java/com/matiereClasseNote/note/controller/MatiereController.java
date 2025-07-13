package com.matiereClasseNote.note.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiereClasseNote.note.dto.MatiereDtoRequest;
import com.matiereClasseNote.note.dto.MatiereDtoResponse;
import com.matiereClasseNote.note.service.MatiereService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/matiere")
public class MatiereController {
    private final MatiereService matiereService;
    @PostMapping("/save")
    public void save(@RequestBody MatiereDtoRequest matiereDtoRequest)
    {
        matiereService.save(matiereDtoRequest);
    }
    @DeleteMapping("/delete/{nom}")
    public void delete(@PathVariable String nom){
        matiereService.delete(nom);
    }

    @PutMapping("/update/{nom}")
    public void update( @RequestBody MatiereDtoRequest matiereDtoRequest, @PathVariable String nom){
        matiereService.update(nom, matiereDtoRequest);
    }
    
    @GetMapping("/one/{nom}")
    public MatiereDtoResponse getNomForMatiere(@PathVariable String nom){
        return matiereService.getNomForMatiere(nom);
    }
    @GetMapping("/all")
    public List<MatiereDtoResponse> getAll(){
        return matiereService.getAll();
    }


}
