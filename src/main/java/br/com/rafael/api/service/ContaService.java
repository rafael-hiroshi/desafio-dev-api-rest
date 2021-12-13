package br.com.rafael.api.service;

import br.com.rafael.api.controller.form.ContaDepositoForm;
import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.controller.form.ContaSaqueForm;
import br.com.rafael.api.controller.form.ContaStatusForm;
import br.com.rafael.api.error.exception.RecursoNaoEncontradoException;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;
import br.com.rafael.api.model.Transacao;
import br.com.rafael.api.repository.ContaRepository;
import br.com.rafael.api.repository.PessoaRepository;
import br.com.rafael.api.repository.TransacaoRepository;
import br.com.rafael.api.specification.SpecificationTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class ContaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public Conta cadastrar(ContaForm contaForm) {
        Long id = contaForm.getIdPessoa();
        Pessoa pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));

        Conta conta = contaForm.converter(pessoa);
        contaRepository.save(conta);
        return conta;
    }

    @Transactional
    public Conta depositar(Long id, ContaDepositoForm form) {
        Long idOrigem = form.getIdContaOrigem();
        BigDecimal valor = form.getValor();

        Conta contaDestino = contaRepository.findContaAtivaPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        Conta contaOrigem = contaRepository.findContaAtivaPorId(idOrigem).orElseThrow(() -> new RecursoNaoEncontradoException(idOrigem));

        contaOrigem.deposita(contaDestino, valor);

        transacaoRepository.save(new Transacao(contaOrigem, valor.negate()));
        transacaoRepository.save(new Transacao(contaDestino, valor));

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        return contaOrigem;
    }

    public Conta consultar(Long id) {
        return contaRepository.findContaAtivaPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }

    @Transactional
    public Conta alterarStatus(Long id, ContaStatusForm form) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));

        conta.setFlagAtivo(form.getFlagAtivo());
        contaRepository.save(conta);

        return conta;
    }

    @Transactional
    public Conta sacar(Long id, ContaSaqueForm form) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
        BigDecimal valorSaque = form.getValor();

        conta.sacar(valorSaque);
        contaRepository.save(conta);
        transacaoRepository.save(new Transacao(conta, valorSaque.negate()));

        return conta;
    }

    public Page<Transacao> imprimirExtrato(Long id, String dataInicial, String dataFim, Pageable pageable) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));

        LocalDateTime dataInicialFormatada = formataData(dataInicial, true);
        LocalDateTime dataFinalFormatada = formataData(dataFim, false);

        return transacaoRepository.findAll(SpecificationTransacao
                .filtra(conta, dataInicialFormatada, dataFinalFormatada), pageable);
    }

    private LocalDateTime formataData(String data, boolean ehInicial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;

        if (data == null) {
            return null;
        }

        localDate = LocalDate.parse(data, formatter);

        if (ehInicial) {
            return localDate.atStartOfDay();
        }
        return localDate.atTime(LocalTime.MAX);
    }
}
