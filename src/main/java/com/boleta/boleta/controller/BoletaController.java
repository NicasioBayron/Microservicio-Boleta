package com.boleta.boleta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boleta.boleta.model.Boleta;
import com.boleta.boleta.service.BoletaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<List<Boleta>> getAll() {
        log.info("Obteniendo todas las boletas");
        return boletaService.getAll();
    }

    @GetMapping("/{idBoleta}")
    public ResponseEntity<Boleta> getById(@PathVariable Long idBoleta) {
        log.info("Obteniendo boleta con id: " + idBoleta);
        return boletaService.getById(idBoleta);
    }

    @PostMapping
    public ResponseEntity<Boleta> create(@RequestBody Boleta boleta) {
        log.info("Creando boleta");
        return boletaService.create(boleta);
    }

    @PutMapping("/{idBoleta}")
    public ResponseEntity<Boleta> update(@PathVariable Long idBoleta, @RequestBody Boleta boleta) {
        log.info("Actualizando boleta con id: " + idBoleta);
        return boletaService.update(idBoleta, boleta);
    }

    @DeleteMapping("/{idBoleta}")
    public ResponseEntity<Boleta> delete(@PathVariable Long idBoleta) {
        log.info("Eliminando boleta con id: " + idBoleta);
        return boletaService.delete(idBoleta);
    }
}
