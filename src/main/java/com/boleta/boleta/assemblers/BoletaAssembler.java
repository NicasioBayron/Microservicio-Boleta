package com.boleta.boleta.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.boleta.boleta.controller.BoletaController;
import com.boleta.boleta.model.Boleta;

@Component
public class BoletaAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>> {
    @Override
    public EntityModel<Boleta> toModel(Boleta boleta) {
        return EntityModel.of(boleta,
                linkTo(methodOn(BoletaController.class).getById(boleta.getIdBoleta())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).update(boleta.getIdBoleta(), boleta))
                        .withRel("Actualizar boleta").withType("PUT"),
                linkTo(methodOn(BoletaController.class).delete(boleta.getIdBoleta()))
                        .withRel("Eliminar boleta").withType("DELETE"));
    }
}
