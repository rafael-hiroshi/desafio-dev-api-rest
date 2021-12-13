package br.com.rafael.api.controller;

import br.com.rafael.api.controller.dto.ContaDetalheDto;
import br.com.rafael.api.controller.form.ContaDepositoForm;
import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("cadastrar")
    public ResponseEntity<ContaDetalheDto> cadastrar(@Valid @RequestBody ContaForm contaForm, UriComponentsBuilder builder) {
        Conta conta = contaService.cadastrar(contaForm);

        URI uri = builder.path("/conta/{id}").buildAndExpand(conta.getIdConta()).toUri();
        return ResponseEntity.created(uri).body(new ContaDetalheDto(conta));
    }

    @PostMapping("depositar/{id}")
    public ResponseEntity<?> depositar(@PathVariable Long id, @Valid @RequestBody ContaDepositoForm contaDepositoForm) {
        Conta conta = contaService.depositar(id, contaDepositoForm);

        return ResponseEntity.ok(new ContaDetalheDto(conta));
    }
}
