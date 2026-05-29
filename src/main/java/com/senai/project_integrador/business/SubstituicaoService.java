package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.Substituicao;
import com.senai.project_integrador.infrastructure.repository.SubstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class SubstituicaoService {

    private final SubstituicaoRepository repository;

    // =========================
    // CREATE
    // =========================
    @Transactional
    public Substituicao registrarSubstituicao(
            Substituicao substituicao
    ) {

        return repository.save(substituicao);
    }

    // =========================
    // READ
    // =========================
    public List<Substituicao> listarTodas() {
        return repository.findAll();
    }

    public Substituicao buscarPorId(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Substituição não encontrada com ID: " + id
                        )
                );
    }

    // =========================
    // UPDATE
    // =========================
    @Transactional
    public Substituicao atualizarSubstituicao(
            Integer id,
            Substituicao novaSubstituicao
    ) {

        Substituicao substituicaoExistente = buscarPorId(id);

        substituicaoExistente.setCoordenador(
                novaSubstituicao.getCoordenador()
        );

        substituicaoExistente.setProfessorSubstituto(
                novaSubstituicao.getProfessorSubstituto()
        );

        substituicaoExistente.setSolicitacaoAusencia(
                novaSubstituicao.getSolicitacaoAusencia()
        );

        return repository.save(substituicaoExistente);
    }

    // =========================
    // DELETE
    // =========================
    @Transactional
    public void cancelarSubstituicao(Integer id) {

        Substituicao substituicao = buscarPorId(id);

        repository.delete(substituicao);
    }
}
