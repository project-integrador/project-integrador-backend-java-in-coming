package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.CoordenadorService;
import com.senai.project_integrador.business.ProfessorService;
import com.senai.project_integrador.business.dto.LoginRequestDTO;
import com.senai.project_integrador.business.dto.LoginResponseDTO;
import com.senai.project_integrador.infrastructure.entities.Coordenador;
import com.senai.project_integrador.infrastructure.entities.Professor;
import com.senai.project_integrador.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AuthController {

    private final ProfessorService professorService;
    private final CoordenadorService coordenadorService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        // PROFESSOR
        Professor professor = professorService.buscarProfessorEmail(request.email());
        if (professor != null && passwordEncoder.matches(request.password(), professor.getPassword())) {
            String token = tokenService.gerarToken(professor.getEmail());
            return ResponseEntity.ok(new LoginResponseDTO(token, "PROFESSOR"));
        }

        // COORDENADOR
        Coordenador coordenador = coordenadorService.buscarCoordenadorPorEmail(request.email());
        if (coordenador != null && passwordEncoder.matches(request.password(), coordenador.getPassword())) {
            String token = tokenService.gerarToken(coordenador.getEmail());
            return ResponseEntity.ok(new LoginResponseDTO(token, "COORDENADOR"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}