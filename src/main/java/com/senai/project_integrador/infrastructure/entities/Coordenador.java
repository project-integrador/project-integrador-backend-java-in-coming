package com.senai.project_integrador.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "coordenadores") // Nome atualizado e sem barra
public class Coordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajustado para IDENTITY (padrão MySQL)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String password; // Adicionado para o Coordenador conseguir logar

    // --- NOVAS COLUNAS ABAIXO ---

    @Column(nullable = false, unique = true, length = 20)
    private String matricula; // Matrícula funcional do coordenador

    @Column(length = 15)
    private String telefone;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true; // Para saber se o coordenador ainda trabalha lá
}