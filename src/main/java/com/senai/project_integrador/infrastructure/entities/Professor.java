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
@Table(name = "professores") // Removida a barra e colocado no plural
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    // --- NOVAS COLUNAS ABAIXO ---

    @Column(nullable = false, unique = true, length = 20)
    private String matricula; // Registro na instituição (ex: RE, RA, Matricula)

    @Column(length = 15)
    private String telefone;

    @Column(name = "area_atuacao", length = 100)
    private String areaAtuacao; // Ex: Tecnologia da Informação, Matemática, Administração

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true; // Soft delete: false significa que não dá mais aula lá
}
