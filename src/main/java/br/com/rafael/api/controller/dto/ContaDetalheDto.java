package br.com.rafael.api.controller.dto;

import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.TipoConta;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaDetalheDto {

    private Long idConta;
    private Long idPessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private Boolean flagAtivo;
    private TipoConta tipoConta;
    private LocalDate dataCriacao;

    public ContaDetalheDto(Conta conta) {
        this.idConta = conta.getIdConta();
        this.idPessoa = conta.getPessoa().getIdPessoa();
        this.saldo = conta.getSaldo();
        this.limiteSaqueDiario = conta.getLimiteSaqueDiario();
        this.flagAtivo = conta.getFlagAtivo();
        this.tipoConta = conta.getTipoConta();
        this.dataCriacao = conta.getDataCriacao();
    }

    public Long getIdConta() {
        return idConta;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public Boolean getFlagAtivo() {
        return flagAtivo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
