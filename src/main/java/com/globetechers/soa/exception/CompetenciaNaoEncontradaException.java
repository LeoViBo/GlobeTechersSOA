package com.globetechers.soa.exception;

public class CompetenciaNaoEncontradaException extends RuntimeException {
    public CompetenciaNaoEncontradaException(Long id) {
        super("Competência não encontrada com o ID: " + id);
    }
}