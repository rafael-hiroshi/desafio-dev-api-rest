package br.com.rafael.api.controller.form;


import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;

public class ContaStatusForm {

    @NotNull @AssertFalse
    private Boolean flagAtivo;

    public ContaStatusForm(Boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public ContaStatusForm() {}

    public Boolean getFlagAtivo() {
        return flagAtivo;
    }
}
