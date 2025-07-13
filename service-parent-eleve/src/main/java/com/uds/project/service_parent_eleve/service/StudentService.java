package com.uds.project.service_parent_eleve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uds.project.service_parent_eleve.entity.Student;
import com.uds.project.service_parent_eleve.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll()
    {
        return studentRepository.findAll();
    }
    
    public void create(Student request) {
        studentRepository.save(request);   
    }

    public Student get(Long id){
        return studentRepository.findById(id).get();
    }

    public void Delete(Long id)
    {
        studentRepository.delete(studentRepository.findById(id).get());
    }

    public void update(Long id,Student profil)
    {
        Student profil2 = studentRepository.findById(id).get();
        profil2.setNom(profil.getNom());
        profil2.setPrenom(profil.getPrenom());
        studentRepository.save(profil2);
    }
}

