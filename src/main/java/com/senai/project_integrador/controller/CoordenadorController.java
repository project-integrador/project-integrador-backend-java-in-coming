package com.senai.project_integrador.controller;

import com.senai.project_integrador.business.CoordenadorService;
import com.senai.project_integrador.infrastructure.entities.Coordenador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List; // ← import no topo

@RestController
@RequestMapping("/coordenador")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<Coordenador> salvarCoordenador(@RequestBody Coordenador coordenador){
        Coordenador salvo = coordenadorService.salvarCoordenador(coordenador);
        // Retornar 201 (CREATED) é a melhor prática para POST
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); 
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Coordenador>> buscarTodos() {
    return ResponseEntity.ok(coordenadorService.buscarTodosCoordenadores());
    }

    @GetMapping
    public ResponseEntity<Coordenador> buscarCoordenadorPorEmail(@RequestParam String email){
        return ResponseEntity.ok(coordenadorService.buscarCoordenadorPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarCoordenadorPorEmail(@RequestParam String email){
        coordenadorService.deletarCoordenadorPorEmail(email);
        return ResponseEntity.noContent().build(); // 204 No Content é ideal para Delete
    }

    // Mudança para @PathVariable: a URL fica /coordenador/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Coordenador> atualizarCoordenadorPorId(@PathVariable Integer id, 
                                                                 @RequestBody Coordenador coordenador){
        Coordenador atualizado = coordenadorService.atualizarCoordenadorPorId(id, coordenador);
        return ResponseEntity.ok(atualizado);
    }
}
