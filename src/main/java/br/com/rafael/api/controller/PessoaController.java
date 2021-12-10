package br.com.rafael.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class PessoaController {

    @GetMapping
    public String test() {
        return "Testando";
    }
}
