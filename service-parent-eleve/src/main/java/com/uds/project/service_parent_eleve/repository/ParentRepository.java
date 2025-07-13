package com.uds.project.service_parent_eleve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.project.service_parent_eleve.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findByUserId(String userId);
}
