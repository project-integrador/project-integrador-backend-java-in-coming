package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.SolicitacaoAusencia;
import com.senai.project_integrador.infrastructure.repository.SolicitacaoAusenciaRepository;
import com.senai.project_integrador.infrastructure.repository.SubstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitacaoAusenciaService {

    private final SolicitacaoAusenciaRepository repository;
    private final SubstituicaoRepository substituicaoRepository; // ← NOVO

    @Transactional
    public SolicitacaoAusencia criarSolicitacao(SolicitacaoAusencia solicitacao) {
        solicitacao.setStatus("PENDENTE");
        return repository.save(solicitacao);
    }

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

    public List<SolicitacaoAusencia> buscarPorEmailProfessor(String email) {
        return repository.findByProfessorEmail(email);
    }

    @Transactional
    public SolicitacaoAusencia atualizarSolicitacao(Integer id, SolicitacaoAusencia dados) {
        SolicitacaoAusencia existente = buscarPorId(id);

        if (dados.getDataAusencia() != null) existente.setDataAusencia(dados.getDataAusencia());
        if (dados.getMotivo() != null && !dados.getMotivo().isBlank()) existente.setMotivo(dados.getMotivo());
        if (dados.getStatus() != null && !dados.getStatus().isBlank()) existente.setStatus(dados.getStatus());

        return repository.save(existente);
    }

    @Transactional
    public SolicitacaoAusencia atualizarStatus(Integer id, String novoStatus) {
        SolicitacaoAusencia existente = buscarPorId(id);
        existente.setStatus(novoStatus);
        return repository.save(existente);
    }

    @Transactional
    public void aprovarOuRejeitarSolicitacao(Integer id, String novoStatus) {
        SolicitacaoAusencia existente = buscarPorId(id);
        existente.setStatus(novoStatus);
        repository.save(existente);
    }

    @Transactional
    public void deletarSolicitacao(Integer id) {
        SolicitacaoAusencia existente = buscarPorId(id);
        // Deleta a substituição vinculada primeiro (se existir) para evitar erro de FK
        substituicaoRepository.deleteBySolicitacaoAusenciaId(id);
        repository.delete(existente);
    }
}
