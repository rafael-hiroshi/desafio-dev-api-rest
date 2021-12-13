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
    private String payload;

    @BeforeAll
    public static void setup() throws URISyntaxException {
        rotaCadastrar = new URI("/conta/cadastrar");
    }

    @Test
    public void testCriarContaValidaDeveRetornarStatusCode201() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", "1")
                .put("tipoConta", "1")
                .toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(rotaCadastrar)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void testCriarContaComPessoaNaoCadastradaDeveRetornarStatusCode404() throws Exception {
        payload = new JSONObject()
            .put("idPessoa", "999")
            .put("tipoConta", "1")
            .toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(rotaCadastrar)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void testCriarContaComTipoDeContaInvalidoDeveRetornarStatusCode400() throws Exception {
        payload = new JSONObject()
                .put("idPessoa", "1")
                .put("tipoConta", "0")
                .toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(rotaCadastrar)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }}
