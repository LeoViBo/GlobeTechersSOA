package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRILHA_COMPETENCIAGT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaCompetenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRILHA_ID", nullable = false)
    private TrilhaModel trilha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPETENCIA_ID", nullable = false)
    private CompetenciaModel competencia;

    @Column(name = "NIVEL_ESPERADO", nullable = true)
    private Integer nivelEsperado;
}
