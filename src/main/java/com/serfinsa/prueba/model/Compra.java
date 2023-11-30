package com.serfinsa.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serfinsa.prueba.dto.CompraPeticionDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_compra")
    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(name = "tipo_pago", length= 50)
    private String tipoPago;

    @Column(name = "monto_total")
    private double montoTotal;

    @ManyToOne
    @JoinColumn(name = "id_comercio", nullable = false,
            foreignKey = @ForeignKey(name = "FK-comercio"))
    private Comercio comercio;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false,
            foreignKey = @ForeignKey(name = "FK-cliente"))
    private Cliente cliente;

    public CompraPeticionDto toDTO() {
        return CompraPeticionDto.builder().id(this.id).fechaCompra(this.fechaCompra).tipoPago(this.tipoPago).montoTotal(this.montoTotal).comercio(comercio.toDTO()).cliente(this.cliente.toDTO()).build();
    }
}
