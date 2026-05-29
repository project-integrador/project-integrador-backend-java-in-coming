package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.Professor;
import com.senai.project_integrador.infrastructure.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;

    @SuppressWarnings("null")
    @Transactional
    public Professor salvarProfessor(Professor professor) {
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        return repository.save(professor);
    }

    public Professor buscarProfessorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @SuppressWarnings("null")
    @Transactional
    public void deletarProfessorPorEmail(String email) {
        Professor professor = buscarProfessorEmail(email);
        repository.delete(professor);
    }

    @SuppressWarnings("null")
    @Transactional
    public Professor atualizarProfessorPorId(Integer id, Professor professorAtualizado) {
        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + id));

        if (professorAtualizado.getNome() != null) {
            existente.setNome(professorAtualizado.getNome());
        }

        if (professorAtualizado.getEmail() != null) {
            existente.setEmail(professorAtualizado.getEmail());
        }

        if (professorAtualizado.getPassword() != null) {
            existente.setPassword(passwordEncoder.encode(professorAtualizado.getPassword()));
        }

        return repository.save(existente);
    }

    public List<Professor> buscarTodosProfessores() {
        return repository.findAll();
    }
}
