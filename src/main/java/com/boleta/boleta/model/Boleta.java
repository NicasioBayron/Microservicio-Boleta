package com.boleta.boleta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "boletas")
@AllArgsConstructor
@NoArgsConstructor
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBoleta")
    private Long idBoleta;

    @Column(name = "idPago")
    private Long idPago;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "montoNeto")
    private Integer montoNeto;

    @Column(name = "impuestoIva")
    private Integer impuestoIva;

    @Column(name = "montoTotal")
    private Integer montoTotal;

    @Column(name = "fechaEmision")
    private LocalDateTime fechaEmision;
}
