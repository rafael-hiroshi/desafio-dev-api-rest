package br.com.rafael.api.exception;

import org.springframework.http.HttpStatus;

public class SaldoInsuficienteException extends BaseHttpException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente para realizar esta operação", HttpStatus.BAD_REQUEST);
    }
}
