package com.boleta.boleta.service;

import org.springframework.stereotype.Service;

import com.boleta.boleta.DTO.BoletaDTO;
import com.boleta.boleta.model.Boleta;
import com.boleta.boleta.repository.BoletaRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepository;

    public List<BoletaDTO> getAll() {
        log.info("Obteniendo todas las boletas");
        return boletaRepository.findAll().stream()
                .map(BoletaDTO::fromModel)
                .collect(Collectors.toList());
    }

    public BoletaDTO getById(Long idBoleta) {
        log.info("Obteniendo boleta con id: " + idBoleta);
        return BoletaDTO.fromModel(boletaRepository.findById(idBoleta).get());
    }

    public BoletaDTO create(BoletaDTO boleta) {
        log.info("Creando boleta");
        Boleta boleta2 = boleta.toModel();
        return BoletaDTO.fromModel(boletaRepository.save(boleta2));
    }

    public BoletaDTO update(Long idBoleta, BoletaDTO cambios) {
        log.info("Actualizando boleta con id: " + idBoleta);
        Boleta boletaExistente = boletaRepository.findById(idBoleta).orElseThrow();
        boletaExistente.setIdPago(cambios.getIdPago());
        boletaExistente.setIdUsuario(cambios.getIdUsuario());
        boletaExistente.setMontoNeto(cambios.getMontoNeto());
        boletaExistente.setImpuestoIva(cambios.getImpuestoIva());
        boletaExistente.setMontoTotal(cambios.getMontoTotal());
        boletaExistente.setFechaEmision(cambios.getFechaEmision());
        return BoletaDTO.fromModel(boletaRepository.save(boletaExistente));
    }

    public void delete(Long idBoleta) {
        log.info("Eliminando boleta con id: " + idBoleta);
        boletaRepository.deleteById(idBoleta);
    }
}
