package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.AulaService;
import com.senai.project_integrador.infrastructure.entities.Aula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AulaController {

    private final AulaService aulaService;

    @PostMapping
    public ResponseEntity<Aula> salvarAula(@RequestBody Aula aula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaService.salvarAula(aula));
    }

    @GetMapping // ← agora aceita ?email= opcional
    public ResponseEntity<List<Aula>> listar(@RequestParam(required = false) String email) {
        if (email != null && !email.isEmpty()) {
            return ResponseEntity.ok(aulaService.buscarPorEmailProfessor(email));
        }
        return ResponseEntity.ok(aulaService.listarTodasAsAulas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(aulaService.buscarAulaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizarAula(@PathVariable Integer id, @RequestBody Aula aula) {
        return ResponseEntity.ok(aulaService.atualizarAulaPorId(id, aula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAula(@PathVariable Integer id) {
        aulaService.deletarAulaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
