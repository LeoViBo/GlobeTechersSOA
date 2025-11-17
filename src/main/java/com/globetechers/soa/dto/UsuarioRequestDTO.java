package com.globetechers.soa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.")
    @Size(max = 150, message = "O email não pode exceder 150 caracteres.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.", groups = {Creation.class})
    @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "A área de atuação é obrigatória.")
    private String areaAtuacao;

    @NotBlank(message = "O nível de carreira é obrigatório.")
    private String nivelCarreira;

    private LocalDate dataCadastro;

    public interface Creation {}
    public interface Update {}
}