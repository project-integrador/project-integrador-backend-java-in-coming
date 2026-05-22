package com.senai.project_integrador.infrastructure.repository;

import com.senai.project_integrador.infrastructure.entities.Substituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubstituicaoRepository
        extends JpaRepository<Substituicao, Integer> {

    // =========================
    // BUSCAR POR PROFESSOR
    // =========================
    List<Substituicao> findByProfessorSubstitutoId(
            Integer professorSubstitutoId
    );

    // =========================
    // BUSCAR POR COORDENADOR
    // =========================
    List<Substituicao> findByCoordenadorId(
            Integer coordenadorId
    );

    // =========================
    // BUSCAR POR SOLICITAÇÃO
    // =========================
    Optional<Substituicao> findBySolicitacaoAusenciaId(
            Integer solicitacaoAusenciaId
    );

    // =========================
    // VERIFICAR EXISTÊNCIA
    // =========================
    boolean existsBySolicitacaoAusenciaId(
            Integer solicitacaoAusenciaId
    );
}