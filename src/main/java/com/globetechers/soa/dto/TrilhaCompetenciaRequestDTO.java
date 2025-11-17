package com.globetechers.soa.dto;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaCompetenciaRequestDTO {

    @NotNull(message = "O ID da Trilha é obrigatório.")
    private Long trilhaId;

    @NotNull(message = "O ID da Competência é obrigatório.")
    private Long competenciaId;

    @Nullable 
    private Integer nivelEsperado;
}