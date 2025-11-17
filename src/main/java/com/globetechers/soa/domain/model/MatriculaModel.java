package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "MATRICULASGT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRILHA_ID", nullable = false)
    private TrilhaModel trilha;

    @Column(name = "DATA_INSCRICAO", nullable = false)
    private LocalDate dataInscricao;

    @Column(name = "STATUS", length = 50, nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "MATRICULA_COMPETENCIAGT",
        joinColumns = @JoinColumn(name = "MATRICULA_ID"),
        inverseJoinColumns = @JoinColumn(name = "COMPETENCIA_ID")
    )
    private Set<CompetenciaModel> competencias;
}
