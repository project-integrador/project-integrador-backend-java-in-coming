package com.senai.project_integrador.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "solicitacoes_ausencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SolicitacaoAusencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate dataAusencia;

    @Column(length = 500)
    private String motivo;

    @Column(nullable = false)
    private String status; // Ex: PENDENTE, APROVADA, REJEITADA, RESOLVIDA

    // Relacionamento: Qual aula ele vai faltar
    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    // Relacionamento: Qual professor está avisando que vai faltar
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
}
