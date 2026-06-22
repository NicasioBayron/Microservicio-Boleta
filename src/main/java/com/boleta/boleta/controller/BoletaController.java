package com.boleta.boleta.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boleta.boleta.assemblers.BoletaAssembler;

import com.boleta.boleta.model.Boleta;
import com.boleta.boleta.service.BoletaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/boletas")
public class BoletaController {
    private final BoletaService boletaService;
    private final BoletaAssembler boletaAssembler;

    public BoletaController(BoletaService boletaService, BoletaAssembler boletaAssembler) {
        this.boletaService = boletaService;
        this.boletaAssembler = boletaAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Boleta>> getAll() {
        log.info("Obteniendo boletas");
        List<EntityModel<Boleta>> boletas = boletaService.getAll().stream()
                .map(boletaAssembler::toModel)
                .collect(Collectors.toList());
        CollectionModel<EntityModel<Boleta>> modelo = CollectionModel.of(boletas,
                linkTo(methodOn(BoletaController.class).getAll()).withSelfRel());
        modelo.add(linkTo(methodOn(BoletaController.class).create(null)).withRel("Crear boleta").withType("POST"));
        return modelo;
    }

    @GetMapping("/{idBoleta}")
    public EntityModel<Boleta> getById(@PathVariable Long idBoleta) {
        log.info("Obteniendo boleta con id: " + idBoleta);
        Boleta boleta = boletaService.getById(idBoleta);
        EntityModel<Boleta> modelo = boletaAssembler.toModel(boleta);
        modelo.add(linkTo(methodOn(BoletaController.class).getAll()).withRel("Todas las boletas").withType("GET"));
        return modelo;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Boleta>> create(@RequestBody Boleta boleta) {
        log.info("Creando boleta");
        return ResponseEntity.ok(boletaAssembler.toModel(boletaService.create(boleta)));
    }

    @PutMapping("/{idBoleta}")
    public ResponseEntity<EntityModel<Boleta>> update(@PathVariable Long idBoleta, @RequestBody Boleta boleta) {
        log.info("Actualizando boleta con id: " + idBoleta);
        return ResponseEntity.ok(boletaAssembler.toModel(boletaService.update(idBoleta, boleta)));
    }

    @DeleteMapping("/{idBoleta}")
    public ResponseEntity<EntityModel<Boleta>> delete(@PathVariable Long idBoleta) {
        log.info("Eliminando boleta con id: " + idBoleta);
        boletaService.delete(idBoleta);
        return ResponseEntity.noContent().build();
    }
}
