package br.com.rafael.api.error.exception;

import org.springframework.http.HttpStatus;

public class LimiteDiarioExcedidoException extends BaseHttpException {

    public LimiteDiarioExcedidoException() {
        super("Não foi possível realizar esta operação. Limite de saque diário excedido.", HttpStatus.BAD_REQUEST);
    }
}
