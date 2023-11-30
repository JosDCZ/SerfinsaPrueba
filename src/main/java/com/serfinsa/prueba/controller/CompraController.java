package com.serfinsa.prueba.controller;

import com.serfinsa.prueba.dto.CompraEnvioDto;
import com.serfinsa.prueba.dto.CompraPeticionDto;
import com.serfinsa.prueba.service.ICompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/compra")
public class CompraController {

    private final ICompraService compraService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<CompraPeticionDto> registrar(@Valid @RequestBody CompraEnvioDto compra) {
        return ResponseEntity.ok(compraService.registrar(compra));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CompraPeticionDto>> listar() {
        List<CompraPeticionDto> compras = compraService.listar();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<CompraPeticionDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(compraService.leerPorId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CompraPeticionDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody CompraEnvioDto compra) {
        return ResponseEntity.ok(compraService.actualizar(id, compra));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CompraPeticionDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(compraService.eliminar(id));
    }
    @GetMapping("/consulta/{idComercio}")
    public ResponseEntity<List<CompraPeticionDto>> obtenerComprasPorComercioYFechaYMedioPago(
            @PathVariable UUID idComercio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String medioPago
    ) {
        List<CompraPeticionDto> compras = compraService.listarComprasComercioYPorFechaYMedioDePago(fecha, idComercio, medioPago);
        return ResponseEntity.ok(compras);
    }
}
