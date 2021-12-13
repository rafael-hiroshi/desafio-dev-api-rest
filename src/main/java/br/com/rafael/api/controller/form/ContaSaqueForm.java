package br.com.rafael.api.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ContaSaqueForm {

    @Positive @NotNull
    private BigDecimal valor;

    public ContaSaqueForm(BigDecimal valor) {
        this.valor = valor;
    }

    public ContaSaqueForm() {}

    public BigDecimal getValor() {
        return valor;
    }
}
