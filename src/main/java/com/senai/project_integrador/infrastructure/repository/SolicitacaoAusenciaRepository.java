package com.senai.project_integrador.infrastructure.repository;

import com.senai.project_integrador.infrastructure.entities.SolicitacaoAusencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoAusenciaRepository extends JpaRepository<SolicitacaoAusencia, Integer> {

    List<SolicitacaoAusencia> findByStatus(String status);

    List<SolicitacaoAusencia> findByProfessorEmail(String email); 
}