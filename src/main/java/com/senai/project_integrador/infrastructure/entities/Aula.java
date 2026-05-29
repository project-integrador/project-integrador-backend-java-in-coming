package com.senai.project_integrador.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aulas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nomeDisciplina;

    @Column(nullable = false)
    private Integer cargaHoraria;

    // Relacionamento: Uma aula pertence a um Professor titular
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professorTitular;
}
