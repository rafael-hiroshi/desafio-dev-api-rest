package br.com.rafael.api.controller.dto;

import br.com.rafael.api.model.Conta;

import java.math.BigDecimal;

public class ContaSaldoDto {

    private BigDecimal saldo;

    public ContaSaldoDto(Conta conta) {
        this.saldo = conta.getSaldo();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
