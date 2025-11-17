package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.TrilhaCompetenciaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaCompetenciaResponseDTO {

    private Long id;
    private TrilhaResponseDTO trilha;
    private CompetenciaResponseDTO competencia;
    private Integer nivelEsperado;

    public static TrilhaCompetenciaResponseDTO fromModel(TrilhaCompetenciaModel model) {
        return TrilhaCompetenciaResponseDTO.builder()
                .id(model.getId())
                .trilha(TrilhaResponseDTO.fromModel(model.getTrilha())) 
                .competencia(CompetenciaResponseDTO.fromModel(model.getCompetencia()))
                .nivelEsperado(model.getNivelEsperado())
                .build();
    }
}