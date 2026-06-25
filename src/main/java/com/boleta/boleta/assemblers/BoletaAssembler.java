package com.boleta.boleta.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.boleta.boleta.DTO.BoletaDTO;
import com.boleta.boleta.controller.BoletaController;

@Component
public class BoletaAssembler implements RepresentationModelAssembler<BoletaDTO, EntityModel<BoletaDTO>> {
        @Override
        public EntityModel<BoletaDTO> toModel(BoletaDTO boletaDTO) {
                return EntityModel.of(boletaDTO,
                                linkTo(methodOn(BoletaController.class).getById(boletaDTO.getIdBoleta())).withSelfRel(),
                                linkTo(methodOn(BoletaController.class).update(boletaDTO.getIdBoleta(),
                                                boletaDTO))
                                                .withRel("Actualizar boleta").withType("PUT"),
                                linkTo(methodOn(BoletaController.class).delete(boletaDTO.getIdBoleta()))
                                                .withRel("Eliminar boleta").withType("DELETE"));
        }
}
