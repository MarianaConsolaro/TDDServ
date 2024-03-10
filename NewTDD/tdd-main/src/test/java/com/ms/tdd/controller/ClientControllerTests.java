package com.ms.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.tdd.TddApplicationTests;
import com.ms.tdd.model.Client;
//import org.junit.Before;
//import org.junit.Test;
//import com.ms.tdd.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTests extends TddApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testClientList() throws Exception {
        this.mockMvc.perform(get("/clients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testCreateClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(new Client(null, "Mariana", "02564897523", "mariana.consolaro@gmail.com", "9994545429")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testFindById() throws Exception {
        String clientId = "65eb1a3669568b43470ef7df"; //--> TESTE PASSA
        //String clientId = "id"; --> TESTE NÃO PASSA

        this.mockMvc.perform(get("/clients/{id}", clientId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));

        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));
    }
    // TINHA NO PROJETO DO NEUBER
    //this.mockMvc.perform( MockMvcRequestBuilders
    //.get("/clients/{id}", clientId)
    //.content(asJsonString(new Client(id:"65eb1a3669568b43470ef7df"))
    //.contentType(MediaType.APPLICATION_JSON)
    //.accept(MediaType.APPLICATION_JSON))
    //  .andExpect(MockMvcResultMatchers.status().isOk())
    //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    //}

    public static String thisIsAJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    //OUTRA FORMA PELA ALURA
        /*
        @Test
        public void testFindById(String Id){
            return ClientRepository.findById(Id)
                    .orElseThrow(() -> new ClientNotFoundException("Este id não foi encontrado"));
        }

         */

    @Test
    public void deleteClientTest() throws Exception {
        String clientId = "65eb4bff21458909c5c0785f";
        // String clientId = id ou 1; ->> TESTE DE FALHA

        this.mockMvc.perform(delete("/clients/{id}", clientId))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));
    }

    public static String thisAJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}