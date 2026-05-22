package com.senai.project_integrador.infrastructure.repository;

import com.senai.project_integrador.infrastructure.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByEmail(String email);

    void deleteByEmail(String email);
}