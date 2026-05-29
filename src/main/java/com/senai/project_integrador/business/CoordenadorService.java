package com.senai.project_integrador.business;

import com.senai.project_integrador.infrastructure.entities.Coordenador;
import com.senai.project_integrador.infrastructure.repository.CoordenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordenadorService {

    private final CoordenadorRepository repository;
    private final PasswordEncoder passwordEncoder;

    @SuppressWarnings("null")
    @Transactional
    public Coordenador salvarCoordenador(Coordenador coordenador) {
        coordenador.setPassword(passwordEncoder.encode(coordenador.getPassword()));
        return repository.save(coordenador);
    }

    public Coordenador buscarCoordenadorPorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public List<Coordenador> buscarTodosCoordenadores() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Transactional
    public void deletarCoordenadorPorEmail(String email) {
        Coordenador coordenador = buscarCoordenadorPorEmail(email);
        repository.delete(coordenador);
    }

    @SuppressWarnings("null")
    @Transactional
    public Coordenador atualizarCoordenadorPorId(Integer id, Coordenador coordenadorAtualizado) {
        Coordenador existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com o ID: " + id));

        if (coordenadorAtualizado.getNome() != null) {
            existente.setNome(coordenadorAtualizado.getNome());
        }

        if (coordenadorAtualizado.getEmail() != null) {
            existente.setEmail(coordenadorAtualizado.getEmail());
        }

        if (coordenadorAtualizado.getPassword() != null) {
            existente.setPassword(passwordEncoder.encode(coordenadorAtualizado.getPassword()));
        }

        return repository.save(existente);
    }
}
