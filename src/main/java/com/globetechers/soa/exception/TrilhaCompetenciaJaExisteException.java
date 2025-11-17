package com.globetechers.soa.exception;

public class TrilhaCompetenciaJaExisteException extends RuntimeException {
    public TrilhaCompetenciaJaExisteException(Long trilhaId, Long competenciaId) {
        super("O vínculo entre Trilha ID " + trilhaId + " e Competência ID " + competenciaId + " já existe.");
    }
}