package br.com.rafael.api.controller;

import br.com.rafael.api.controller.dto.ContaDetalheDto;
import br.com.rafael.api.controller.dto.ContaSaldoDto;
import br.com.rafael.api.controller.dto.TransacaoDetalheDto;
import br.com.rafael.api.controller.form.*;
import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Transacao;
import br.com.rafael.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDetalheDto> cadastrar(@Valid @RequestBody ContaForm contaForm, UriComponentsBuilder builder) {
        Conta conta = contaService.cadastrar(contaForm);

        URI uri = builder.path("/conta/{id}").buildAndExpand(conta.getIdConta()).toUri();
        return ResponseEntity.created(uri).body(new ContaDetalheDto(conta));
    }

    @PostMapping("{id}/depositar")
    public ResponseEntity<ContaDetalheDto> depositar(@PathVariable Long id, @Valid @RequestBody ContaDepositoForm form) {
        Conta conta = contaService.depositar(id, form);

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

    @GetMapping("{id}/extrato")
    public Page<TransacaoDetalheDto> imprimirExtrato(@RequestParam(required = false) String dataInicial,
           @RequestParam(required = false) String dataFim, @PathVariable Long id,
           @PageableDefault(sort = "dataTransacao") Pageable pageable) {
        Page<Transacao> transacaoPage = contaService.imprimirExtrato(id, dataInicial, dataFim, pageable);

        return TransacaoDetalheDto.converter(transacaoPage);
    }
}
