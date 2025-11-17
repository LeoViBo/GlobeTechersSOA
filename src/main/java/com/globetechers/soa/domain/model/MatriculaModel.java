package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id", nullable = false)
    private TrilhaModel trilha;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @Column(length = 50, nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "matricula_competencia",
        joinColumns = @JoinColumn(name = "matricula_id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )
    private Set<CompetenciaModel> competencias;
}