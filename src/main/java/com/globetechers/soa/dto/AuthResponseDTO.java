package com.globetechers.soa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    private String token;

    @Builder.Default
    private String tipo = "Bearer";

    private Long idUsuario;

    private String login;

    private List<String> roles;

    private String mensagem;
}