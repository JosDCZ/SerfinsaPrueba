package com.serfinsa.prueba.serviceImpl;

import com.serfinsa.prueba.dto.ComercioDto;
import com.serfinsa.prueba.exceptions.CustomException;
import com.serfinsa.prueba.model.Comercio;
import com.serfinsa.prueba.repository.IComercioRepository;
import com.serfinsa.prueba.service.IComercioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComercioServiceImpl implements IComercioService {

    private final IComercioRepository comercioRepository;

    @Override
    public ComercioDto registrar(ComercioDto data) {
        if (comercioRepository.existsByNombre(data.getNombre())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El comercio ya está registrado");
        }
        return comercioRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public ComercioDto leerPorId(UUID id) {
        Comercio comercio = comercioRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra comercio"));
        return comercio.toDTO();
    }

    @Override
    public List<ComercioDto> listar() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Comercio> comercios = this.comercioRepository.findAll(sort);
        return comercios.stream().map(Comercio::toDTO).toList();
    }

    @Override
    public ComercioDto actualizar(UUID id, ComercioDto data) {
        if (comercioRepository.existsByNombreAndIdNot(data.getNombre(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El comercio ya está registrado");
        }
        data.setId(id);
        return comercioRepository.save(data.toEntity()).toDTO();
    }

    @Override
    public ComercioDto eliminar(UUID id) {
        ComercioDto comercioDto = leerPorId(id);
        comercioRepository.delete(comercioDto.toEntity());
        return comercioDto;
    }
}
