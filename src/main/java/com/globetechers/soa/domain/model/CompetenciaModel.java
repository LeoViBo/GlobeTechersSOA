package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_COMPETENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String areaConhecimento; 
}