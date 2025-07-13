package com.uds.project.service_parent_eleve.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parent_student")
@Getter
@Setter
public class ParentStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}

