package br.com.rafael.api.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static URI rotaCadastrar;
    private static URI rotaDepositar;
    private String payload;

    @BeforeAll
    public static void setup() throws URISyntaxException {
        rotaCadastrar = new URI("/conta/cadastrar");
        rotaDepositar = new URI("/conta/depositar");
    }

    @Test
    public void testCriarContaValidaDeveRetornarStatusCode201() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", 1)
                .put("tipoConta", 1)
                .toString();

        doRequest(rotaCadastrar, 201);
    }

    @Test
    public void testCriarContaComPessoaNaoCadastradaDeveRetornarStatusCode404() throws Exception {
        payload = new JSONObject()
            .put("idPessoa", 999)
            .put("tipoConta", 1)
            .toString();

        doRequest(rotaCadastrar, 404);
    }

    @Test
    public void testCriarContaComTipoDeContaInvalidoDeveRetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", 1)
                .put("tipoConta", 0)
                .toString();

        doRequest(rotaCadastrar, 400);
    }

    @Test
    public void testDepositarComSaldoInsuficienteDeveRetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("idContaOrigem", 4)
                .put("idContaDestino", 1)
                .put("valor", new BigDecimal(200))
                .toString();

        doRequest(rotaDepositar, 400);
    }

    @Test
    public void testDepositarComSaldoSuficienteDeveRetornarStatusCode200() throws Exception {
        payload = new JSONObject()
                .put("idContaOrigem", 1)
                .put("idContaDestino", 4)
                .put("valor", new BigDecimal(1000))
                .toString();

        doRequest(rotaDepositar, 200);
    }

    private void doRequest(URI rota, int statusCode) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(rota)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(statusCode));
    }
}
