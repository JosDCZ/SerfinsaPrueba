package com.serfinsa.prueba.dto;

import com.serfinsa.prueba.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ClienteDto {

    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 75, message = "El nombre debe tener entre 2 y 75 caracteres")
    private String nombre;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 9, message = "El nombre debe tener entre 2 y 9 caracteres, con guion")
    private String telefono;

    public Cliente toEntity() {
        return Cliente.builder().id(this.id).nombre(this.nombre).telefono(this.telefono).build();
    }
}
