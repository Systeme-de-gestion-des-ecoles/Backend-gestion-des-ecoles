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

import com.matiereClasseNote.note.dto.NoteDtoRequest;
import com.matiereClasseNote.note.dto.NoteDtoResponse;
import com.matiereClasseNote.note.service.NoteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")

public class NoteController {
    private final NoteService noteServiceImplement;

    @PostMapping("/save")
    public void save(@RequestBody NoteDtoRequest noteDtoReques )
    {
        noteServiceImplement.save(noteDtoReques);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id)
    {
        noteServiceImplement.delete(id);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable long id,@RequestBody NoteDtoRequest noteDtoRequest)
    {
        noteServiceImplement.update(id, noteDtoRequest);
    }

    @GetMapping("/matiere/{idMatiere}")
    public List<NoteDtoResponse> getNoteForMatiere(@PathVariable String idMatiere)
    {
       return noteServiceImplement.getNoteForMatiere(idMatiere);
    }

    @GetMapping("/eleve/{idEleve}")
    public List<NoteDtoResponse> getNoteForEleve(@PathVariable String idEleve)
    {
        return noteServiceImplement.getNoteForEleve(idEleve);
    }
}
