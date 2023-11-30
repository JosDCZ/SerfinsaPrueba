package com.serfinsa.prueba.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serfinsa.prueba.exceptions.CustomException;
import com.serfinsa.prueba.model.Cliente;
import com.serfinsa.prueba.model.Comercio;
import com.serfinsa.prueba.model.Compra;
import com.serfinsa.prueba.repository.IClienteRepository;
import com.serfinsa.prueba.repository.IComercioRepository;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CompraEnvioDto {

    private UUID id;

    @NotNull(message = "La fecha de compra es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaCompra;

    @NotBlank(message = "El tipo pago es obligatorio")
    @Size(min = 2, max = 50, message = "El tipo pago debe tener entre 2 y 50 caracteres")
    private String tipoPago;

    @NotNull(message = "El monto total es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El monto total debe ser un número válido con máximo 10 dígitos en total y 2 decimales")
    @Min(value = 0, message = "El monto total debe ser mayor o igual a 0")
    private double montoTotal;

    private UUID comercio;

    private UUID cliente;
    
    public Compra toEntitySave(IComercioRepository comercioRepository, IClienteRepository clienteRepository) {
        Comercio comerciobuscar = comercioRepository.findById(this.comercio).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro comercio"));

        Cliente clientebuscar = clienteRepository.findById(this.cliente).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro cliente"));

        return Compra.builder().id(this.id).fechaCompra(this.fechaCompra).tipoPago(this.tipoPago).montoTotal(this.montoTotal).comercio(comerciobuscar).cliente(clientebuscar).build();
    }
}
