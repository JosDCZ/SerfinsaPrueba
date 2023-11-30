package com.serfinsa.prueba.repository;

import com.serfinsa.prueba.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface ICompraRepository extends JpaRepository<Compra, UUID> {
    List<Compra> findByFechaCompraBetweenAndComercio_IdAndTipoPago(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            UUID idComercio,
            String tipoPago
    );
}
