package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.CompetenciaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria; 
    private String areaConhecimento;

    public static CompetenciaResponseDTO fromModel(CompetenciaModel model) {
        if (model == null) return null;
        return CompetenciaResponseDTO.builder()
                .id(model.getId())
                .nome(model.getNome())
                .descricao(model.getDescricao())
                .categoria(model.getCategoria())
                .areaConhecimento(model.getAreaConhecimento()) 
                .build();
    }
}