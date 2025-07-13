package com.matiereClasseNote.note.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.matiereClasseNote.note.model.Student;

@FeignClient(name = "Student", url = "http://localhost:8081/api/student")
public interface StudentServiceCustum {
    @GetMapping("/get/{id}")
    public Student getStudent(@PathVariable Long id);

    @GetMapping("/getByClass/{id}")
    public List<Student> getStudentClass(@PathVariable String id );
}
