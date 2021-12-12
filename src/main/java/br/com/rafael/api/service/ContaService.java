package br.com.rafael.api.service;

import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Pessoa;
import br.com.rafael.api.repository.ContaRepository;
import br.com.rafael.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public Conta cadastrar(ContaForm contaForm) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(contaForm.getIdPessoa());

        if (!pessoa.isPresent()) {
            throw new IllegalArgumentException("Pessoa não cadastrada!");
        }

        Conta conta = contaForm.converter(pessoa.get());
        contaRepository.save(conta);
        return conta;
    }
}
