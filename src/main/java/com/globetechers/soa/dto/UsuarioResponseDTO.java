package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String areaAtuacao; 
    private String nivelCarreira;

    public static UsuarioResponseDTO fromModel(UsuarioModel model) {
        if (model == null) return null;
        return UsuarioResponseDTO.builder()
                .id(model.getId())
                .nomeCompleto(model.getNomeCompleto())
                .email(model.getUsername())
                .areaAtuacao(model.getAreaAtuacao())
                .nivelCarreira(model.getNivelCarreira())
                .build();
    }
}