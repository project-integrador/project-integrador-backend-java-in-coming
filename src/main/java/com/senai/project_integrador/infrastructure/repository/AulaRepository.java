package com.senai.project_integrador.infrastructure.repository;

import com.senai.project_integrador.infrastructure.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findByProfessorTitularEmail(String email);
}