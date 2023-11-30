package com.serfinsa.prueba.controller;

import com.serfinsa.prueba.dto.ClienteDto;
import com.serfinsa.prueba.service.IClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final IClienteService clienteService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<ClienteDto> registrar(@Valid @RequestBody ClienteDto cliente) {
        return ResponseEntity.ok(clienteService.registrar(cliente));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDto>> listar() {
        List<ClienteDto> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ClienteDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.leerPorId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ClienteDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody ClienteDto cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ClienteDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.eliminar(id));
    }
}
