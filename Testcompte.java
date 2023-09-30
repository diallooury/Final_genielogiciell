
package com.example.demo1;
import ServiceCompte.CompteService;
import com.example.demo1.Controller.CompteController; // Import correct
import com.example.demo1.Model.Comptes;
import com.example.demo1.Reopsitory.CompteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class Testcompte {

    private MockMvc mockMvc;

    @Mock
    private CompteService compteService;

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private CompteController compteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(compteController).build();
    }

    @Test
    public void testCreateCompte() throws Exception {
        Comptes compte = new Comptes();

        ObjectMapper objectMapper = new ObjectMapper();
        String compteJson = objectMapper.writeValueAsString(compte);

        // Mock the repository to return the saved object with generated ID
        when(compteRepository.save(any(Comptes.class))).thenAnswer(invocation -> {
            Comptes savedCompte = invocation.getArgument(0);
            savedCompte.setId(1L); // Simulate setting the generated ID
            return savedCompte;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(compteJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists()) ;// Expect "id" field in the JSON response

        verify(compteRepository, times(1)).save(any(Comptes.class));
        verifyNoMoreInteractions(compteRepository);
    }

}