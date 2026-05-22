package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.SolicitacaoAusenciaService;
import com.senai.project_integrador.infrastructure.entities.SolicitacaoAusencia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/solicitacao")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class SolicitacaoAusenciaController {

    private final SolicitacaoAusenciaService solicitacaoService;

    @PostMapping
    public ResponseEntity<SolicitacaoAusencia> criarSolicitacao(@RequestBody SolicitacaoAusencia solicitacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.criarSolicitacao(solicitacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoAusencia> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitacaoService.buscarPorId(id));
    }

    @GetMapping("/professor")
    public ResponseEntity<List<SolicitacaoAusencia>> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(solicitacaoService.buscarPorEmailProfessor(email));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<SolicitacaoAusencia>> listarPendentes() {
        return ResponseEntity.ok(solicitacaoService.listarSolicitacoesPendentes());
    }

    @GetMapping("/todas")
    public ResponseEntity<List<SolicitacaoAusencia>> listarTodas() {
        return ResponseEntity.ok(solicitacaoService.buscarTodas());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SolicitacaoAusencia> atualizarStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(solicitacaoService.atualizarStatus(id, status));
    }
}