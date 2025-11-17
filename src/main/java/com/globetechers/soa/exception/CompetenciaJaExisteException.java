package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CompetenciaJaExisteException extends RuntimeException {
    public CompetenciaJaExisteException(String nome) {
        super("A Competência com o nome '" + nome + "' já está cadastrada no sistema.");
    }
}