package com.boleta.boleta.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.boleta.boleta.DTO.BoletaDTO;
import com.boleta.boleta.assemblers.BoletaAssembler;
import com.boleta.boleta.service.BoletaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

@WebMvcTest(BoletaController.class)
@Import(BoletaAssembler.class)
public class BoletaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BoletaService boletaService;

    private ObjectMapper objectMapper;

    private BoletaDTO boletaDto;
    private BoletaDTO boletaDtoResponse;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // DTO de respuesta (lo que devuelve el servicio)
        boletaDtoResponse = new BoletaDTO();
        boletaDtoResponse.setIdBoleta(1L);
        boletaDtoResponse.setIdPago(1L);
        boletaDtoResponse.setIdUsuario(1L);
        boletaDtoResponse.setMontoNeto(1500);
        boletaDtoResponse.setImpuestoIva(285);
        boletaDtoResponse.setMontoTotal(1785);
        boletaDtoResponse.setFechaEmision(LocalDateTime.of(2026, 6, 25, 12, 0, 0));

        // DTO de entrada (lo que envia el cliente en POST/PUT)
        boletaDto = new BoletaDTO();
        boletaDto.setIdPago(1L);
        boletaDto.setIdUsuario(1L);
        boletaDto.setMontoNeto(1500);
    }

    @Test
    public void testListarBoletas() throws Exception {
        when(boletaService.getAll()).thenReturn(List.of(boletaDtoResponse));

        mockMvc.perform(get("/boletas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testObtenerBoleta() throws Exception {
        when(boletaService.getById(1L)).thenReturn(boletaDtoResponse);

        mockMvc.perform(get("/boletas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPago").value(1))
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.montoNeto").value(1500))
                .andExpect(jsonPath("$.impuestoIva").value(285))
                .andExpect(jsonPath("$.montoTotal").value(1785))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testCrearBoleta() throws Exception {
        when(boletaService.create(any(BoletaDTO.class))).thenReturn(boletaDtoResponse);

        mockMvc.perform(post("/boletas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boletaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montoNeto").value(1500))
                .andExpect(jsonPath("$.montoTotal").value(1785))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testActualizarBoleta() throws Exception {
        when(boletaService.update(eq(1L), any(BoletaDTO.class))).thenReturn(boletaDtoResponse);

        mockMvc.perform(put("/boletas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boletaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montoNeto").value(1500))
                .andExpect(jsonPath("$.montoTotal").value(1785))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testEliminarBoleta() throws Exception {
        doNothing().when(boletaService).delete(1L);

        mockMvc.perform(delete("/boletas/1"))
                .andExpect(status().isNoContent());

        verify(boletaService, times(1)).delete(1L);
    }
}