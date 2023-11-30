package com.serfinsa.prueba.model;

import com.serfinsa.prueba.dto.ClienteDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente")
    private UUID id;

    @Column(name = "nombre", length= 75, unique = true)
    private String nombre;

    @Column(name = "telefono", length= 9, unique = true)
    private String telefono;

    public ClienteDto toDTO() {
        return ClienteDto.builder().id(this.id).nombre(this.nombre).telefono(this.telefono).build();
    }
}
