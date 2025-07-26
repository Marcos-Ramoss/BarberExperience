package com.barberexperience.presentation.controllers;

import com.barberexperience.application.gattewars.barbearia.*;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.infrastructure.persistence.mappers.BarbeariaResponseMapper;
import com.barberexperience.presentation.dtos.BarbeariaResponse;
import com.barberexperience.presentation.dtos.CriarBarbeariaRequest;
import com.barberexperience.presentation.dtos.AtualizarBarbeariaRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/barbearias")
@RequiredArgsConstructor
@Tag(name = "Barbearia")
@SecurityRequirement(name = "bearerAuth")
public class BarbeariaController {
    
    private final CriarBarbeariaUseCase criarBarbeariaUseCase;
    private final BuscarBarbeariaPorIdUseCase buscarBarbeariaPorIdUseCase;
    private final ListarBarbeariasUseCase listarBarbeariasUseCase;
    private final ExcluirBarbeariaUseCase excluirBarbeariaUseCase;
    private final AtualizarBarbeariaUseCase atualizarBarbeariaUseCase;
    
    @PostMapping
    @Operation(summary = "Criar uma nova barbearia")
    public ResponseEntity<BarbeariaResponse> criarBarbearia(@RequestBody CriarBarbeariaRequest request) {
        BarbeariaDomain barbearia = criarBarbeariaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BarbeariaResponseMapper.toDto(barbearia));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar barbearia por ID")
    public ResponseEntity<BarbeariaResponse> buscarBarbeariaPorId(@PathVariable Long id) {
        return buscarBarbeariaPorIdUseCase.execute(id)
                .map(barbearia -> ResponseEntity.ok(BarbeariaResponseMapper.toDto(barbearia)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Listar todas as barbearias")
    public ResponseEntity<List<BarbeariaResponse>> listarBarbearias() {
        List<BarbeariaDomain> barbearias = listarBarbeariasUseCase.execute();
        List<BarbeariaResponse> response = barbearias.stream()
                .map(BarbeariaResponseMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar barbearia")
    public ResponseEntity<BarbeariaResponse> atualizarBarbearia(@PathVariable Long id, @RequestBody AtualizarBarbeariaRequest request) {
        BarbeariaDomain barbearia = atualizarBarbeariaUseCase.execute(id, request);
        return ResponseEntity.ok(BarbeariaResponseMapper.toDto(barbearia));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir barbearia")
    public ResponseEntity<Void> excluirBarbearia(@PathVariable Long id) {
        excluirBarbeariaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
} 