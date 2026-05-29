package com.senai.project_integrador.infrastructure.repository;

import com.senai.project_integrador.infrastructure.entities.Substituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubstituicaoRepository extends JpaRepository<Substituicao, Integer> {

    List<Substituicao> findByProfessorSubstitutoId(Integer professorSubstitutoId);

    List<Substituicao> findByCoordenadorId(Integer coordenadorId);

    Optional<Substituicao> findBySolicitacaoAusenciaId(Integer solicitacaoAusenciaId);

    boolean existsBySolicitacaoAusenciaId(Integer solicitacaoAusenciaId);

    // ← NOVO: necessário para deletar a substituição antes de deletar a solicitação
    void deleteBySolicitacaoAusenciaId(Integer solicitacaoAusenciaId);
}
