package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMPETENCIAGT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100, unique = true)
    private String nome;

    @Column(name = "CATEGORIA", nullable = false, length = 50)
    private String categoria;

    @Column(name = "DESCRICAO", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "AREA_CONHECIMENTO", nullable = false, length = 100)
    private String areaConhecimento; 
}
