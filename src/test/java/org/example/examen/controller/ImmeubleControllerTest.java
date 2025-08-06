package org.example.examen.controller;

import org.example.examen.model.Immeuble;
import org.example.examen.service.ImmeubleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImmeubleController.class)
@AutoConfigureMockMvc(addFilters = false)
class ImmeubleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImmeubleService service;

    @Test
    void getAllReturnsListOfImmeubles() throws Exception {
        Immeuble immeuble = new Immeuble();
        immeuble.setId(1L);
        immeuble.setNom("Tour 1");
        when(service.findAll()).thenReturn(List.of(immeuble));

        mockMvc.perform(get("/api/immeubles").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nom").value("Tour 1"));
    }
}
