package br.com.rafael.api.model;


public enum TipoConta {
    CONTA_CORRENTE(1),
    CONTA_POUPANCA(2);

    private Integer tipoConta;

    TipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }
}
