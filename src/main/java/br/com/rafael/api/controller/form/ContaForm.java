package br.com.rafael.api.controller.form;

import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;

import javax.validation.constraints.*;

public class ContaForm {

    @Positive @NotNull
    private Long idPessoa;
    @Positive @NotNull @Min(1) @Max(2)
    private Integer tipoConta;

    public ContaForm(Long idPessoa, Integer tipoConta) {
        this.idPessoa = idPessoa;
        this.tipoConta = tipoConta;
    }

    public Conta converter(Pessoa pessoa) {
        return new Conta(pessoa, tipoConta);
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }
}
