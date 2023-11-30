package com.serfinsa.prueba.dto;

import com.serfinsa.prueba.model.Compra;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CompraPeticionDto {

    private UUID id;

    private LocalDateTime fechaCompra;

    private String tipoPago;

    private double montoTotal;

    private ComercioDto comercio;

    private ClienteDto cliente;

    public Compra toEntity() {
        return Compra.builder().id(this.id).fechaCompra(this.fechaCompra).tipoPago(this.tipoPago).montoTotal(this.montoTotal).comercio(comercio.toEntity()).cliente(this.cliente.toEntity()).build();
    }
}
