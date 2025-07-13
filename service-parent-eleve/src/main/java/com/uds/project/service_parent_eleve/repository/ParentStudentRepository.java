package com.uds.project.service_parent_eleve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.project.service_parent_eleve.entity.ParentStudent;

public interface ParentStudentRepository extends JpaRepository<ParentStudent, Long> {
    List<ParentStudent> findByParentId(Long parentId);
    boolean existsByParentIdAndStudentId(Long parentId, Long studentId);
}
