package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TrilhaJaExisteException extends RuntimeException {
    public TrilhaJaExisteException(String nome) {
        super("A Trilha de Aprendizagem com o nome '" + nome + "' já está cadastrada no sistema.");
    }
}