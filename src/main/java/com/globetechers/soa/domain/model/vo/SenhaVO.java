package com.globetechers.soa.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SenhaVO {

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 60, max = 60, message = "A senha deve ser armazenada com 60 caracteres (BCrypt).")
    @Column(name = "senha", nullable = false, length = 60)
    private String value;

    protected SenhaVO() {}

    public SenhaVO(String value) { this.value = value; }

    public String getValue() { return value; }
}