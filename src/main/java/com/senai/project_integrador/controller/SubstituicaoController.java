package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.SubstituicaoService;
import com.senai.project_integrador.infrastructure.entities.Substituicao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/substituicao")
@RequiredArgsConstructor
public class SubstituicaoController {

    private final SubstituicaoService substituicaoService;

    @PostMapping
    public ResponseEntity<Substituicao> registrarSubstituicao(@RequestBody Substituicao substituicao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(substituicaoService.registrarSubstituicao(substituicao));
    }

    @GetMapping
    public ResponseEntity<List<Substituicao>> listarTodas() {
        return ResponseEntity.ok(substituicaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Substituicao> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(substituicaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Substituicao> atualizarSubstituicao(
            @PathVariable Integer id,
            @RequestBody Substituicao substituicao) {
        return ResponseEntity.ok(substituicaoService.atualizarSubstituicao(id, substituicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarSubstituicao(@PathVariable Integer id) {
        substituicaoService.cancelarSubstituicao(id);
        return ResponseEntity.noContent().build();
    }
}
