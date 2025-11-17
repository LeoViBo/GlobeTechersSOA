package com.globetechers.soa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    @Positive(message = "O ID do usuário deve ser um valor positivo.")
    private Long usuarioId;

    @NotNull(message = "O ID da trilha é obrigatório.")
    @Positive(message = "O ID da trilha deve ser um valor positivo.")
    private Long trilhaId;
    
    @NotEmpty(message = "A lista de IDs de competências não pode ser vazia.")
    private Set<Long> idsCompetencias;

    private String status;
}