package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.Aula;
import com.senai.project_integrador.infrastructure.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository repository;

    @SuppressWarnings("null")
    @Transactional
    public Aula salvarAula(Aula aula) {
        return repository.save(aula);
    }

    @SuppressWarnings("null")
    public Aula buscarAulaPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada com o ID: " + id));
    }

    public List<Aula> listarTodasAsAulas() {
        return repository.findAll();
    }

    public List<Aula> buscarPorEmailProfessor(String email) {
        return repository.findByProfessorTitularEmail(email);
    }

    @SuppressWarnings("null")
    @Transactional
    public void deletarAulaPorId(Integer id) {
        Aula aula = buscarAulaPorId(id);
        repository.delete(aula);
    }

    @SuppressWarnings("null")
    @Transactional
    public Aula atualizarAulaPorId(Integer id, Aula aulaAtualizada) {
        Aula existente = buscarAulaPorId(id);

        if (aulaAtualizada.getNomeDisciplina() != null) {
            existente.setNomeDisciplina(aulaAtualizada.getNomeDisciplina());
        }
        if (aulaAtualizada.getCargaHoraria() != null) {
            existente.setCargaHoraria(aulaAtualizada.getCargaHoraria());
        }
        if (aulaAtualizada.getProfessorTitular() != null
                && aulaAtualizada.getProfessorTitular().getId() != null) {
            existente.setProfessorTitular(aulaAtualizada.getProfessorTitular());
        }

        return repository.save(existente);
    }
}