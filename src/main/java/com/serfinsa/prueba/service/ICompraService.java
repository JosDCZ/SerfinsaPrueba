package com.serfinsa.prueba.service;

import com.serfinsa.prueba.dto.CompraEnvioDto;
import com.serfinsa.prueba.dto.CompraPeticionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ICompraService {

    CompraPeticionDto registrar(CompraEnvioDto data);

    CompraPeticionDto leerPorId(UUID id);

    List<CompraPeticionDto> listar();

    CompraPeticionDto actualizar(UUID id, CompraEnvioDto data);

    CompraPeticionDto eliminar(UUID id);

    List<CompraPeticionDto> listarComprasComercioYPorFechaYMedioDePago(
            LocalDate fecha,
            UUID idComercio,
            String tipoPago
    );
}
