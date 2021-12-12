package br.com.rafael.api.service;

import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.exception.RecursoNaoEncontradoException;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;
import br.com.rafael.api.repository.ContaRepository;
import br.com.rafael.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;

@Service
public class ContaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaRepository contaRepository;

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
}
