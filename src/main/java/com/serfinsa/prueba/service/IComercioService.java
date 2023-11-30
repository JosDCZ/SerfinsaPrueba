package com.serfinsa.prueba.service;

import com.serfinsa.prueba.dto.ComercioDto;

import java.util.List;
import java.util.UUID;

public interface IComercioService {

    ComercioDto registrar(ComercioDto data);

    ComercioDto leerPorId(UUID id);

    List<ComercioDto> listar();

    ComercioDto actualizar(UUID id, ComercioDto data);

    ComercioDto eliminar(UUID id);
}
