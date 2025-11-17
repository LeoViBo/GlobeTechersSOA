package com.globetechers.soa.domain.model;

import com.globetechers.soa.domain.enums.NivelEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "TRILHASGT") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(min = 5, max = 150)
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL")
    private NivelEnum nivel;

    @NotNull
    @Min(1)
    @Column(name = "CARGA_HORARIA")
    private Integer cargaHoraria;

    @NotBlank
    @Column(name = "FOCO_PRINCIPAL")
    private String focoPrincipal;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRIADOR_ID", nullable = false)
    private UsuarioModel criador;

    @ManyToMany
    @JoinTable(
        name = "TRILHA_COMPETENCIAGT", 
        joinColumns = @JoinColumn(name = "TRILHA_ID"),
        inverseJoinColumns = @JoinColumn(name = "COMPETENCIA_ID")
    )
    private Set<CompetenciaModel> competencias;

    @Column(name = "OBJETIVO_CARREIRA")
    private String objetivoCarreira;
}
