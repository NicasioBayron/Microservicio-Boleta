package com.boleta.boleta.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.boleta.boleta.DTO.BoletaDTO;
import com.boleta.boleta.model.Boleta;
import com.boleta.boleta.repository.BoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTests {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaService boletaService;

    private BoletaDTO boletaDto;
    private Boleta boletaModel;

    @BeforeEach
    void setUp() {
        boletaDto = new BoletaDTO();
        boletaDto.setIdBoleta(1L);
        boletaDto.setIdPago(10L);
        boletaDto.setIdUsuario(100L);
        boletaDto.setMontoNeto(1500);
        boletaDto.setImpuestoIva(285);
        boletaDto.setMontoTotal(1785);
        boletaDto.setFechaEmision(LocalDateTime.of(2026, 6, 25, 12, 0, 0));
        boletaModel = boletaDto.toModel();
    }

    @Test
    void testGetAll() {
        when(boletaRepository.findAll()).thenReturn(List.of(boletaModel));
        List<BoletaDTO> result = boletaService.getAll();
        assertEquals(1, result.size());
        assertEquals(boletaDto.getIdBoleta(), result.get(0).getIdBoleta());
    }

    @Test
    void testGetById() {
        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boletaModel));
        BoletaDTO result = boletaService.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdBoleta());
    }

    @Test
    void testCreate() {
        when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaModel);
        BoletaDTO result = boletaService.create(boletaDto);
        assertNotNull(result);
        assertEquals(boletaDto.getIdPago(), result.getIdPago());
    }


    @Test
    void testUpdate() {
        // Prepare updated DTO
        BoletaDTO updatedDto = new BoletaDTO();
        updatedDto.setIdPago(20L);
        updatedDto.setIdUsuario(200L);
        updatedDto.setMontoNeto(2000);
        updatedDto.setImpuestoIva(380);
        updatedDto.setMontoTotal(2380);
        updatedDto.setFechaEmision(LocalDateTime.now());

        // Mock repository behavior
        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boletaModel));
        when(boletaRepository.save(any(Boleta.class))).thenReturn(updatedDto.toModel());

        // Execute service update
        BoletaDTO result = boletaService.update(1L, updatedDto);

        // Verify returned DTO has updated values
        assertEquals(updatedDto.getIdPago(), result.getIdPago());
        assertEquals(updatedDto.getMontoTotal(), result.getMontoTotal());
    }


    @Test
    void testDelete() {
        doNothing().when(boletaRepository).deleteById(1L);
        boletaService.delete(1L);
        verify(boletaRepository, times(1)).deleteById(1L);
    }
}

