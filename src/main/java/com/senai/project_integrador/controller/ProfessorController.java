package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.ProfessorService;
import com.senai.project_integrador.infrastructure.entities.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor) {
        Professor salvo = service.salvarProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<Professor> buscar(@RequestParam String email) {
        return ResponseEntity.ok(service.buscarProfessorEmail(email));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Professor>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodosProfessores());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam String email) {
        service.deletarProfessorPorEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Integer id,
            @RequestBody Professor professor) {
        Professor atualizado = service.atualizarProfessorPorId(id, professor);
        return ResponseEntity.ok(atualizado);
    }
}