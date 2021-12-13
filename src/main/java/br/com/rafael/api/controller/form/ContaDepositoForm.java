package br.com.rafael.api.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ContaDepositoForm {

    @Positive @NotNull @Min(1)
    private Long idContaOrigem;
    @Positive @NotNull
    private BigDecimal valor;

    public ContaDepositoForm(Long idContaOrigem, BigDecimal valor) {
        this.idContaOrigem = idContaOrigem;
        this.valor = valor;
    }

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
