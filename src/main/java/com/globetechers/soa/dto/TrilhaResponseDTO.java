package com.globetechers.soa.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.globetechers.soa.domain.model.CompetenciaModel;
import com.globetechers.soa.domain.model.TrilhaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String objetivoCarreira;
    private LocalDate dataCriacao;
    private Long criadorId;

    private List<CompetenciaResumoDTO> competencias;

    public static TrilhaResponseDTO fromModel(TrilhaModel model) {
        List<CompetenciaResumoDTO> competenciasResumo = model.getCompetencias().stream()
                .map(CompetenciaResumoDTO::fromModel)
                .collect(Collectors.toList());

        return TrilhaResponseDTO.builder()
                .id(model.getId())
                .nome(model.getNome())
                .descricao(model.getDescricao())
                .objetivoCarreira(model.getObjetivoCarreira())
                .dataCriacao(model.getDataCriacao())
                .criadorId(model.getCriador().getId())
                .competencias(competenciasResumo)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompetenciaResumoDTO {
        private Long id;
        private String nome;
        private String area;

        public static CompetenciaResumoDTO fromModel(CompetenciaModel model) {
            return CompetenciaResumoDTO.builder()
                    .id(model.getId())
                    .nome(model.getNome())
                    .area(model.getCategoria())
                    .build();
        }
    }
}