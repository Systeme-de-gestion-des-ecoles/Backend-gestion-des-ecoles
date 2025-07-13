package com.uds.project.service_parent_eleve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.project.service_parent_eleve.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
   
}
