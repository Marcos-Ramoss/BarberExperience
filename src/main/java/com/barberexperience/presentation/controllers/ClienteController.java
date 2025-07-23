package com.barberexperience.presentation.controllers;

import com.barberexperience.application.gattewars.cliente.BuscarClientePorIdUseCase;
import com.barberexperience.application.gattewars.cliente.CriarClienteUseCase;
import com.barberexperience.application.gattewars.cliente.ExcluirClienteUseCase;
import com.barberexperience.application.gattewars.cliente.ListarClientesUseCase;
import com.barberexperience.application.usecases.cliente.*;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.infrastructure.persistence.mappers.ClienteResponseMapper;
import com.barberexperience.presentation.dtos.ClienteResponse;
import com.barberexperience.presentation.dtos.CriarClienteRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Cliente")
public class ClienteController {
    
    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;
    
    @PostMapping
    @Operation(summary = "Criar um novo cliente")
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody CriarClienteRequest request) {
        ClienteDomain cliente = criarClienteUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteResponseMapper.toDto(cliente));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable Long id) {
        return buscarClientePorIdUseCase.execute(id)
                .map(cliente -> ResponseEntity.ok(ClienteResponseMapper.toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteDomain> clientes = listarClientesUseCase.execute();
        List<ClienteResponse> response = clientes.stream()
                .map(ClienteResponseMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        excluirClienteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
} 