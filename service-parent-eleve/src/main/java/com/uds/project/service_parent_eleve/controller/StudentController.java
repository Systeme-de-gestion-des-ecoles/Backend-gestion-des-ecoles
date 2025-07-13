package com.uds.project.service_parent_eleve.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.project.service_parent_eleve.entity.Student;
import com.uds.project.service_parent_eleve.service.StudentService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public void createStudent( @RequestBody Student request) {
        studentService.create(request);
    }

    @GetMapping("/get/{id}")
    public Student get(@PathVariable Long id)
    {
        return studentService.get(id);
    }

    @GetMapping("/get")
    public List<Student> getAll()
    {
        return studentService.getAll();
    }
    @PutMapping("/update/{id}")
    public void Update(@PathVariable Long id,@RequestBody Student student)
    {
        studentService.update(id, student);
    }

}
