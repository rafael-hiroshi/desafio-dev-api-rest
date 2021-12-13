package br.com.rafael.api.model;

import br.com.rafael.api.error.exception.LimiteDiarioExcedidoException;
import br.com.rafael.api.error.exception.SaldoInsuficienteException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contas")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    private BigDecimal saldo = BigDecimal.ZERO;
    private BigDecimal limiteSaqueDiario;
    private Boolean flagAtivo = true;
    @Convert(converter = TipoContaConverter.class)
    private TipoConta tipoConta;
    private LocalDate dataCriacao = LocalDate.now();
    @OneToMany(mappedBy = "idConta")
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta(Pessoa pessoa, Integer tipoConta, BigDecimal limiteSaqueDiario) {
        this.pessoa = pessoa;
        this.tipoConta = new TipoContaConverter().convertToEntityAttribute(tipoConta);
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public Conta() {}

    public Long getIdConta() {
        return idConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
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

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setFlagAtivo(Boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public void deposita(Conta destino, BigDecimal valor) {
        if (valor.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException();
        }

        this.saldo = this.saldo.subtract(valor);
        destino.saldo = destino.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException();
        }
        if (valor.compareTo(this.limiteSaqueDiario) > 0) {
            throw new LimiteDiarioExcedidoException();
        }

        this.saldo = this.saldo.subtract(valor);
    }
}
