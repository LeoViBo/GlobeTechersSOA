package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MatriculaJaExisteException extends RuntimeException {

    public MatriculaJaExisteException(Long usuarioId, Long trilhaId) {
        super("O usuário " + usuarioId + " já está matriculado na trilha " + trilhaId + ".");
    }
}