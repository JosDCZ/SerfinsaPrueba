package com.serfinsa.prueba.service;

import com.serfinsa.prueba.dto.ClienteDto;

import java.util.List;
import java.util.UUID;

public interface IClienteService {

    ClienteDto registrar(ClienteDto data);

    ClienteDto leerPorId(UUID id);

    List<ClienteDto> listar();

    ClienteDto actualizar(UUID id, ClienteDto data);

    ClienteDto eliminar(UUID id);
}
