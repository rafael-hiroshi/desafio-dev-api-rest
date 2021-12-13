package br.com.rafael.api.controller;

import br.com.rafael.api.controller.dto.ContaDetalheDto;
import br.com.rafael.api.controller.dto.ContaSaldoDto;
import br.com.rafael.api.controller.form.ContaDepositoForm;
import br.com.rafael.api.controller.form.ContaForm;
import br.com.rafael.api.controller.form.ContaSaqueForm;
import br.com.rafael.api.controller.form.ContaStatusForm;
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

    @PostMapping("depositar")
    public ResponseEntity<ContaDetalheDto> depositar(@Valid @RequestBody ContaDepositoForm form) {
        Conta conta = contaService.depositar(form);

        return ResponseEntity.ok(new ContaDetalheDto(conta));
    }

    @GetMapping("{id}")
    public ResponseEntity<ContaSaldoDto> consultar(@PathVariable Long id) {
        Conta conta = contaService.consultar(id);

        return ResponseEntity.ok(new ContaSaldoDto(conta));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ContaDetalheDto> alterarStatus(@PathVariable Long id, @Valid @RequestBody ContaStatusForm form) {
        Conta conta = contaService.alterarStatus(id, form);

        return ResponseEntity.ok(new ContaDetalheDto(conta));
    }

    @PostMapping("{id}/sacar")
    public ResponseEntity<ContaDetalheDto> sacar(@PathVariable Long id, @Valid @RequestBody ContaSaqueForm form) {
        Conta conta = contaService.sacar(id, form);

        return ResponseEntity.ok(new ContaDetalheDto(conta));
    }
}
