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
        rotaCadastrar = new URI("/conta");
        rotaDepositar = new URI("/conta/depositar");
    }

    @Test
    public void testCriarContaValidaDeveRetornarStatusCode201() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", 1)
                .put("tipoConta", 1)
                .toString();

        post(rotaCadastrar, 201);
    }

    @Test
    public void testCriarContaComPessoaNaoCadastradaDeveFalharERetornarStatusCode404() throws Exception {
        payload = new JSONObject()
            .put("idPessoa", 999)
            .put("tipoConta", 1)
            .toString();

        post(rotaCadastrar, 404);
    }

    @Test
    public void testCriarContaComTipoDeContaInvalidoDeveFalharERetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", 1)
                .put("tipoConta", 0)
                .toString();

        post(rotaCadastrar, 400);
    }

    @Test
    public void testDepositarComSaldoInsuficienteDeveFalharERetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("idContaOrigem", 4)
                .put("idContaDestino", 1)
                .put("valor", new BigDecimal(200))
                .toString();

        post(rotaDepositar, 400);
    }

    @Test
    public void testDepositarComSaldoSuficienteDeveRetornarStatusCode200() throws Exception {
        payload = new JSONObject()
                .put("idContaOrigem", 1)
                .put("idContaDestino", 4)
                .put("valor", new BigDecimal(1000))
                .toString();

        post(rotaDepositar, 200);
    }

    @Test
    public void testDepositarParaContaInativaDeveFalharERetornarStatusCode404() throws Exception {
        payload = new JSONObject()
                .put("idContaOrigem", 1)
                .put("idContaDestino", 80)
                .put("valor", new BigDecimal(1000))
                .toString();

        post(rotaDepositar, 404);
    }

    @Test
    public void testConsultarSaldoDeContaDeveRetornarStatusCode200() throws Exception {
        get(new URI("/conta/1"), 200);
    }

    @Test
    public void testConsultarSaldoDeContaInexistenteDeveFalharERetornarStatusCode404() throws Exception {
        get(new URI("/conta/999"), 404);
    }

    @Test
    public void testInativarContaExistenteDeveRetornarStatusCode200() throws Exception {
        payload = new JSONObject()
                .put("flagAtivo", false)
                .toString();

        patch(new URI("/conta/5"), 200);
    }

    @Test
    public void testReativarContaInativaDeveFalharERetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("flagAtivo", false)
                .toString();

        patch(new URI("/conta/6"), 200);

        payload = new JSONObject()
                .put("flagAtivo", true)
                .toString();

        patch(new URI("/conta/6"), 400);
    }

    @Test
    public void testInativarContaInexistenteDeveFalharERetornarStatusCode404() throws Exception {
        payload = new JSONObject()
                .put("flagAtivo", false)
                .toString();

        patch(new URI("/conta/999"), 404);
    }

    @Test
    public void testSacarQuantidadeIgualAoLimiteDeveRetornarStatusCode200() throws Exception {
        payload = new JSONObject()
                .put("valor", new BigDecimal(1000))
                .toString();

        post(new URI("/conta/1/sacar"), 200);
    }

    @Test
    public void testSacarQuantidadeMaiorQueOLimiteDeveFalharERetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("valor", new BigDecimal(1002))
                .toString();

        post(new URI("/conta/1/sacar"), 400);
    }

    @Test
    public void testSacarQuantidadeIgualAZeroDeveFalharERetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("valor", new BigDecimal(0))
                .toString();

        post(new URI("/conta/1/sacar"), 400);
    }

    @Test
    public void testSacarQuantidadeMaiorQueZeroEAbaixoDoLimiteDeveRetornarStatusCode200() throws Exception {
        payload = new JSONObject()
                .put("valor", new BigDecimal(1))
                .toString();

        post(new URI("/conta/1/sacar"), 200);
    }

    private void get(URI rota, int statusCode) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(rota))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(statusCode));
    }

    private void post(URI rota, int statusCode) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(rota)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(statusCode));
    }

    private void patch(URI rota, int statusCode) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(rota)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(statusCode));
    }
}
