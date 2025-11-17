package com.globetechers.soa.exception;

public class TrilhaCompetenciaNaoEncontradaException extends RuntimeException {
    public TrilhaCompetenciaNaoEncontradaException(Long id) {
        super("Vínculo Trilha-Competência não encontrado com o ID: " + id);
    }
}