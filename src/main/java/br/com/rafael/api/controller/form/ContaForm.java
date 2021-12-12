package br.com.rafael.api.controller.form;

import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ContaForm {

    @Positive @NotNull
    private Long idPessoa;
    @Positive @NotNull @Min(1) @Max(2)
    private Integer tipoConta;
    @PositiveOrZero @Max(2500)
    private BigDecimal limiteSaqueDiario;

    public ContaForm(Long idPessoa, Integer tipoConta, BigDecimal limiteSaqueDiario) {
        this.idPessoa = idPessoa;
        this.tipoConta = tipoConta;

        if (limiteSaqueDiario == null) {
            limiteSaqueDiario = new BigDecimal(1000);
        }

        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public Conta converter(Pessoa pessoa) {
        return new Conta(pessoa, tipoConta, limiteSaqueDiario);
    }
}
