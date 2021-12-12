package br.com.rafael.api.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends BaseHttpException {

    public RecursoNaoEncontradoException(Long id) {
        super("Recurso com id " + id+ " não encontrado", HttpStatus.NOT_FOUND);
    }
}
