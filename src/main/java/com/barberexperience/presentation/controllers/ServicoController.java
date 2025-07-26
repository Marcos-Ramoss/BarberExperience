package com.barberexperience.presentation.controllers;

import com.barberexperience.application.gattewars.servico.*;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.infrastructure.persistence.mappers.ServicoResponseMapper;
import com.barberexperience.presentation.dtos.ServicoResponse;
import com.barberexperience.presentation.dtos.CriarServicoRequest;

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
@RequestMapping("/servicos")
@RequiredArgsConstructor
@Tag(name = "Serviço")
@SecurityRequirement(name = "bearerAuth")
public class ServicoController {
    
    private final CriarServicoUseCase criarServicoUseCase;
    private final BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;
    private final ListarServicosUseCase listarServicosUseCase;
    private final ExcluirServicoUseCase excluirServicoUseCase;
    
    @PostMapping
    @Operation(summary = "Criar um novo serviço")
    public ResponseEntity<ServicoResponse> criarServico(@RequestBody CriarServicoRequest request) {
        ServicoDomain servico = criarServicoUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServicoResponseMapper.toDto(servico));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por ID")
    public ResponseEntity<ServicoResponse> buscarServicoPorId(@PathVariable Long id) {
        return buscarServicoPorIdUseCase.execute(id)
                .map(servico -> ResponseEntity.ok(ServicoResponseMapper.toDto(servico)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os serviços")
    public ResponseEntity<List<ServicoResponse>> listarServicos() {
        List<ServicoDomain> servicos = listarServicosUseCase.execute();
        List<ServicoResponse> response = servicos.stream()
                .map(ServicoResponseMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir serviço")
    public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
        excluirServicoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
} 