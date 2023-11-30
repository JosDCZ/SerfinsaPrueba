package com.serfinsa.prueba.model;

import com.serfinsa.prueba.dto.ComercioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_comercio")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_comercio")
    private UUID id;

    @Column(name = "nombre", length= 75, unique = true)
    private String nombre;

    @Column(name = "ubicacion", length= 255)
    private String ubicacion;

    public ComercioDto toDTO() {
        return ComercioDto.builder().id(this.id).nombre(this.nombre).ubicacion(this.ubicacion).build();
    }
}
