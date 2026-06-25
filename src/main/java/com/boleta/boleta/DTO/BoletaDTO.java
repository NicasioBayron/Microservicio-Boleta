package com.boleta.boleta.DTO;

import java.time.LocalDateTime;

import com.boleta.boleta.model.Boleta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoletaDTO {

    @JsonIgnore
    private Long idBoleta;
    private Long idPago;
    private Long idUsuario;
    @NotNull(message = "El monto neto es requerido")
    private Integer montoNeto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer impuestoIva;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer montoTotal;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaEmision;

    public Boleta toModel() {
        Boleta boleta = new Boleta();
        boleta.setIdBoleta(this.idBoleta);
        boleta.setIdPago(this.idPago);
        boleta.setIdUsuario(this.idUsuario);
        boleta.setMontoNeto(this.montoNeto);
        boleta.setImpuestoIva(this.impuestoIva);
        boleta.setMontoTotal(this.montoTotal);
        boleta.setFechaEmision(this.fechaEmision);
        return boleta;
    }

    public static BoletaDTO fromModel(Boleta boleta) {
        BoletaDTO boletaDTO = new BoletaDTO();
        boletaDTO.setIdBoleta(boleta.getIdBoleta());
        boletaDTO.setIdPago(boleta.getIdPago());
        boletaDTO.setIdUsuario(boleta.getIdUsuario());
        boletaDTO.setMontoNeto(boleta.getMontoNeto());
        boletaDTO.setImpuestoIva(boleta.getImpuestoIva());
        boletaDTO.setMontoTotal(boleta.getMontoTotal());
        boletaDTO.setFechaEmision(boleta.getFechaEmision());
        return boletaDTO;
    }
}
