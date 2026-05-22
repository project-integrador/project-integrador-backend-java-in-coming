package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.SolicitacaoAusencia;
import com.senai.project_integrador.infrastructure.repository.SolicitacaoAusenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitacaoAusenciaService {

    private final SolicitacaoAusenciaRepository repository;

    @Transactional
    public SolicitacaoAusencia criarSolicitacao(SolicitacaoAusencia solicitacao) {
        solicitacao.setStatus("PENDENTE");
        return repository.save(solicitacao);
    }

    @SuppressWarnings("null")
    public SolicitacaoAusencia buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação de ausência não encontrada."));
    }

    public List<SolicitacaoAusencia> listarSolicitacoesPendentes() {
        return repository.findByStatus("PENDENTE");
    }

    public List<SolicitacaoAusencia> buscarTodas() {
        return repository.findAll();
    }

    public List<SolicitacaoAusencia> buscarPorEmailProfessor(String email) { // ← NOVO
        return repository.findByProfessorEmail(email);
    }

    @Transactional
    public void aprovarOuRejeitarSolicitacao(Integer id, String novoStatus) {
        SolicitacaoAusencia existente = buscarPorId(id);
        existente.setStatus(novoStatus);
        repository.save(existente);
    }

    @Transactional
    public SolicitacaoAusencia atualizarStatus(Integer id, String novoStatus) {
        SolicitacaoAusencia existente = buscarPorId(id);
        existente.setStatus(novoStatus);
        return repository.save(existente);
    }
}