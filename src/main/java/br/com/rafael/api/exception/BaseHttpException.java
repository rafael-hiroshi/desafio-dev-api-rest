package br.com.rafael.api.exception;

import org.springframework.http.HttpStatus;

public class BaseHttpException extends RuntimeException {

    private HttpStatus status;

    public BaseHttpException(String message) {
        super(message);
    }

    public BaseHttpException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
