package com.serfinsa.prueba.serviceImpl;

import com.serfinsa.prueba.dto.CompraEnvioDto;
import com.serfinsa.prueba.dto.CompraPeticionDto;
import com.serfinsa.prueba.exceptions.CustomException;
import com.serfinsa.prueba.model.Compra;
import com.serfinsa.prueba.repository.IClienteRepository;
import com.serfinsa.prueba.repository.IComercioRepository;
import com.serfinsa.prueba.repository.ICompraRepository;
import com.serfinsa.prueba.service.ICompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements ICompraService {

    private final ICompraRepository compraRepository;
    private final IComercioRepository comercioRepository;
    private final IClienteRepository clienteRepository;

    @Override
    public CompraPeticionDto registrar(CompraEnvioDto data) {
        return compraRepository.save(data.toEntitySave(comercioRepository, clienteRepository)).toDTO();
    }

    @Override
    public CompraPeticionDto leerPorId(UUID id) {
        Compra compra = compraRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra compra"));
        return compra.toDTO();
    }

    @Override
    public List<CompraPeticionDto> listar() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Compra> compras = this.compraRepository.findAll(sort);
        return compras.stream().map(Compra::toDTO).toList();
    }

    @Override
    public CompraPeticionDto actualizar(UUID id, CompraEnvioDto data) {
        data.setId(id);
        return compraRepository.save(data.toEntitySave(comercioRepository, clienteRepository)).toDTO();
    }

    @Override
    public CompraPeticionDto eliminar(UUID id) {
        CompraPeticionDto compraDto = leerPorId(id);
        compraRepository.delete(compraDto.toEntity());
        return compraDto;
    }

    @Override
    public List<CompraPeticionDto> listarComprasComercioYPorFechaYMedioDePago(
            LocalDate fecha,
            UUID idComercio,
            String tipoPago
    ) {
        LocalDateTime fechaInicioSinHora = fecha.atStartOfDay();

        List<Compra> compras = compraRepository.findByFechaCompraBetweenAndComercio_IdAndTipoPago(
                fechaInicioSinHora,
                fechaInicioSinHora.plusDays(1), // Asegurar que se incluya toda la fecha
                idComercio,
                tipoPago
        );

        compras.sort(Comparator.comparing(Compra::getFechaCompra));

        return compras.stream()
                .map(Compra::toDTO)
                .collect(Collectors.toList());
    }
}
