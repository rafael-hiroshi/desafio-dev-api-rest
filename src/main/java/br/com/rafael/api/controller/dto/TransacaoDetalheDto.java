package br.com.rafael.api.controller.dto;

import br.com.rafael.api.model.Transacao;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDetalheDto {

    private Long id;
    private Long idConta;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    public TransacaoDetalheDto(Transacao transacao) {
        this.id = transacao.getIdTransacao();
        this.idConta = transacao.getIdConta().getIdConta();
        this.valor = transacao.getValor();
        this.dataTransacao = transacao.getDataTransacao();
    }

    public Long getId() {
        return id;
    }

    public Long getIdConta() {
        return idConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public static Page<TransacaoDetalheDto> converter(Page<Transacao> transacoes) {
        return transacoes.map(TransacaoDetalheDto::new);
    }
}
