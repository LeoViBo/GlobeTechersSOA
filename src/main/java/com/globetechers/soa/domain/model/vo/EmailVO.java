package com.globetechers.soa.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EmailVO {

    @Email(message = "O formato do e-mail é inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Column(name = "email", nullable = false, unique = true, length = 100) 
    private String value;

    protected EmailVO() {}

    public EmailVO(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
}