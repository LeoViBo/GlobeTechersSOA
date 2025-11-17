package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.MatriculaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaResponseDTO {

    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private Long trilhaId;
    private String trilhaNome;
    private LocalDate dataInscricao;
    private String status;

    public static MatriculaResponseDTO fromModel(MatriculaModel model) {
        return MatriculaResponseDTO.builder()
                .id(model.getId())
                .usuarioId(model.getUsuario().getId())
                .usuarioNome(model.getUsuario().getNome())
                .trilhaId(model.getTrilha().getId())
                .trilhaNome(model.getTrilha().getNome()) 
                .dataInscricao(model.getDataInscricao())
                .status(model.getStatus())
                .build();
    }
}