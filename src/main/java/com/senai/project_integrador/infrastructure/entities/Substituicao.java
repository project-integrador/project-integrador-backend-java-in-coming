package com.senai.project_integrador.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "substituicoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Substituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Coordenador
    @ManyToOne
    @JoinColumn(
            name = "coordenador_id",
            nullable = false
    )
    private Coordenador coordenador;

    // Professor substituto
    @ManyToOne
    @JoinColumn(
            name = "professor_substituto_id",
            nullable = false
    )
    private Professor professorSubstituto;

    // Solicitação de ausência
    @OneToOne
    @JoinColumn(
            name = "solicitacao_ausencia_id",
            nullable = false
    )
    private SolicitacaoAusencia solicitacaoAusencia;
}
