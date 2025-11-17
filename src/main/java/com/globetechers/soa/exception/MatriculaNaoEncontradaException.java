package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatriculaNaoEncontradaException extends RuntimeException {

    public MatriculaNaoEncontradaException(Long id) {
        super("Matrícula com ID " + id + " não encontrada.");
    }
}