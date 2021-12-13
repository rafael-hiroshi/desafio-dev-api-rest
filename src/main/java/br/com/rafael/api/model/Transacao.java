package br.com.rafael.api.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta idConta;
    private BigDecimal valor;
    @CreationTimestamp
    private Timestamp dataTransacao;

    public Transacao(Conta idConta, BigDecimal valor) {
        this.idConta = idConta;
        this.valor = valor;
    }

    public Transacao() {}

    public Long getIdTransacao() {
        return idTransacao;
    }

    public Conta getIdConta() {
        return idConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Timestamp getDataTransacao() {
        return dataTransacao;
    }
}
