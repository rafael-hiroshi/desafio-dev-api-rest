package br.com.rafael.api.service;

import br.com.rafael.api.controller.form.ContaDepositoForm;
import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.exception.RecursoNaoEncontradoException;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;
import br.com.rafael.api.model.Transacao;
import br.com.rafael.api.repository.ContaRepository;
import br.com.rafael.api.repository.PessoaRepository;
import br.com.rafael.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

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
    public Conta depositar(ContaDepositoForm form) {
        Long idOrigem = form.getIdContaOrigem();
        Long idDestino = form.getIdContaDestino();
        BigDecimal valor = form.getValor();

        Conta contaDestino = contaRepository.findById(idDestino).orElseThrow(() -> new RecursoNaoEncontradoException(idDestino));
        Conta contaOrigem = contaRepository.findById(idOrigem).orElseThrow(() -> new RecursoNaoEncontradoException(idOrigem));

        contaOrigem.deposita(contaDestino, valor);

        Transacao transacaoOrigem = new Transacao(contaOrigem, valor.negate());
        Transacao transacaoDestino = new Transacao(contaDestino, valor);

        transacaoRepository.save(transacaoOrigem);
        transacaoRepository.save(transacaoDestino);
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        return contaOrigem;
    }

    public Conta consultar(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }
}
