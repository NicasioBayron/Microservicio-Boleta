package com.boleta.boleta.service;

import org.springframework.stereotype.Service;

import com.boleta.boleta.model.Boleta;
import com.boleta.boleta.repository.BoletaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoletaService {
    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> getAll() {
        log.info("Obteniendo todas las boletas");
        return boletaRepository.findAll();
    }

    public Boleta getById(Long idBoleta) {
        log.info("Obteniendo boleta con id: " + idBoleta);
        return boletaRepository.findById(idBoleta).get();
    }

    public Boleta create(Boleta boleta) {
        log.info("Creando boleta");
        return boletaRepository.save(boleta);
    }

    public Boleta update(Long idBoleta, Boleta boleta) {
        log.info("Actualizando boleta con id: " + idBoleta);
        Boleta boletaExistente = boletaRepository.findById(idBoleta).get();
        boletaExistente.setIdPago(boleta.getIdPago());
        boletaExistente.setIdUsuario(boleta.getIdUsuario());
        boletaExistente.setMontoNeto(boleta.getMontoNeto());
        boletaExistente.setImpuestoIva(boleta.getImpuestoIva());
        boletaExistente.setMontoTotal(boleta.getMontoTotal());
        boletaExistente.setFechaEmision(boleta.getFechaEmision());
        return boletaRepository.save(boletaExistente);
    }

    public void delete(Long idBoleta) {
        log.info("Eliminando boleta con id: " + idBoleta);
        boletaRepository.deleteById(idBoleta);
    }
}
