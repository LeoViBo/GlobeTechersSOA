package com.globetechers.soa.dto;

import com.globetechers.soa.domain.enums.NivelEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaRequestDTO {

    @NotBlank(message = "O nome da Trilha é obrigatório.")
    @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição da Trilha é obrigatória.")
    @Size(min = 20, max = 500, message = "A descrição deve ter entre 20 e 500 caracteres.")
    private String descricao;

    @NotNull(message = "O nível é obrigatório.")
    private NivelEnum nivel;

    @NotNull(message = "A carga horária é obrigatória.")
    @Min(value = 1, message = "A carga horária deve ser positiva.")
    private Integer cargaHoraria;

    @NotBlank(message = "O foco principal é obrigatório.")
    @Size(max = 100, message = "O foco principal não pode exceder 100 caracteres.")
    private String focoPrincipal;

    @NotNull(message = "O ID do criador é obrigatório.")
    @Min(value = 1, message = "O ID do criador deve ser um valor positivo.")
    private Long criadorId;
}