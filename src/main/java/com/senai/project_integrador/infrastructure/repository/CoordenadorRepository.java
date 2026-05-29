package com.senai.project_integrador.infrastructure.repository;
import com.senai.project_integrador.infrastructure.entities.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Integer> {

    Optional<Coordenador> findByEmail(String email);

    void deleteByEmail(String email);
}
