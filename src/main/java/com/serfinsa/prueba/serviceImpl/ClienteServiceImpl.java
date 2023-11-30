package com.serfinsa.prueba.serviceImpl;

import com.serfinsa.prueba.dto.ClienteDto;
import com.serfinsa.prueba.exceptions.CustomException;
import com.serfinsa.prueba.model.Cliente;
import com.serfinsa.prueba.repository.IClienteRepository;
import com.serfinsa.prueba.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository clienteRepository;

    @Override
    public ClienteDto registrar(ClienteDto data) {
        if (clienteRepository.existsByNombre(data.getNombre())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El cliente ya está registrado");
        }
        return clienteRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public ClienteDto leerPorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra cliente"));
        return cliente.toDTO();
    }

    @Override
    public List<ClienteDto> listar() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Cliente> clientes = this.clienteRepository.findAll(sort);
        return clientes.stream().map(Cliente::toDTO).toList();
    }

    @Override
    public ClienteDto actualizar(UUID id, ClienteDto data) {
        if (clienteRepository.existsByNombreAndIdNot(data.getNombre(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El cliente ya está registrado");
        }
        data.setId(id);
        return clienteRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public ClienteDto eliminar(UUID id) {
        ClienteDto clienteDto = leerPorId(id);
        clienteRepository.delete(clienteDto.toEntity());
        return clienteDto;
    }
}
