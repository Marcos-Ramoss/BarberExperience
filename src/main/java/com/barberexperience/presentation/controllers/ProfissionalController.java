package com.barberexperience.presentation.controllers;

import com.barberexperience.application.gattewars.profissional.BuscarProfissionalPorIdUseCase;
import com.barberexperience.application.gattewars.profissional.CriarProfissionalUseCase;
import com.barberexperience.application.gattewars.profissional.ExcluirProfissionalUseCase;
import com.barberexperience.application.gattewars.profissional.ListarProfissionaisUseCase;
import com.barberexperience.application.usecases.profissional.*;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.infrastructure.persistence.mappers.ProfissionalResponseMapper;
import com.barberexperience.presentation.dtos.ProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
@Tag(name = "Profissional")
public class ProfissionalController {
    
    private final CriarProfissionalUseCase criarProfissionalUseCase;
    private final BuscarProfissionalPorIdUseCase buscarProfissionalPorIdUseCase;
    private final ListarProfissionaisUseCase listarProfissionaisUseCase;
    private final ExcluirProfissionalUseCase excluirProfissionalUseCase;
    
    @PostMapping
    @Operation(summary = "Criar um novo profissional")
    public ResponseEntity<ProfissionalResponse> criarProfissional(@RequestBody CriarProfissionalRequest request) {
        ProfissionalDomain profissional = criarProfissionalUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProfissionalResponseMapper.toDto(profissional));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID")
    public ResponseEntity<ProfissionalResponse> buscarProfissionalPorId(@PathVariable Long id) {
        return buscarProfissionalPorIdUseCase.execute(id)
                .map(profissional -> ResponseEntity.ok(ProfissionalResponseMapper.toDto(profissional)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os profissionais")
    public ResponseEntity<List<ProfissionalResponse>> listarProfissionais() {
        List<ProfissionalDomain> profissionais = listarProfissionaisUseCase.execute();
        List<ProfissionalResponse> response = profissionais.stream()
                .map(ProfissionalResponseMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir profissional")
    public ResponseEntity<Void> excluirProfissional(@PathVariable Long id) {
        excluirProfissionalUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
} 