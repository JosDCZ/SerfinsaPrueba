package com.serfinsa.prueba.controller;

import com.serfinsa.prueba.dto.ComercioDto;
import com.serfinsa.prueba.service.IComercioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comercio")
public class ComercioController {

    private final IComercioService comercioService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<ComercioDto> registrar(@Valid @RequestBody ComercioDto comercio) {
        return ResponseEntity.ok(comercioService.registrar(comercio));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ComercioDto>> listar() {
        List<ComercioDto> comercios = comercioService.listar();
        return ResponseEntity.ok(comercios);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ComercioDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(comercioService.leerPorId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ComercioDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody ComercioDto comercio) {
        return ResponseEntity.ok(comercioService.actualizar(id, comercio));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ComercioDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(comercioService.eliminar(id));
    }
}
