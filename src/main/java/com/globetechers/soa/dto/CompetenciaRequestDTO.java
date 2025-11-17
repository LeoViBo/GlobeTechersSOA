package com.globetechers.soa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompetenciaRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome não pode exceder 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição não pode exceder 1000 caracteres")
    private String descricao;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(max = 50, message = "A categoria não pode exceder 50 caracteres")
    private String categoria;

    @NotBlank(message = "A área de conhecimento é obrigatória")
    @Size(max = 100, message = "A área de conhecimento não pode exceder 100 caracteres")
    private String areaConhecimento;
}