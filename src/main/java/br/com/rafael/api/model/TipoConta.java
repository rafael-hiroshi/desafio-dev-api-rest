package br.com.rafael.api.model;

import java.util.Arrays;

public enum TipoConta {
    CONTA_CORRENTE(1),
    CONTA_POUPANCA(2);

    private Integer tipoConta;

    TipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public static TipoConta getTipoConta(Integer valor) {
        return Arrays.stream(values())
                .filter(t -> t.tipoConta == valor)
                .findFirst().get();
    }
}
