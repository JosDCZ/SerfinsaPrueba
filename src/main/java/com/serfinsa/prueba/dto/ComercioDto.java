package com.serfinsa.prueba.dto;

import com.serfinsa.prueba.model.Comercio;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ComercioDto {

    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 75, message = "El nombre debe tener entre 2 y 75 caracteres")
    private String nombre;

    @NotBlank(message = "La ubicacion es obligatorio")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String ubicacion;

    public Comercio toEntity() {
        return Comercio.builder().id(this.id).nombre(this.nombre).ubicacion(this.ubicacion).build();
    }
}
