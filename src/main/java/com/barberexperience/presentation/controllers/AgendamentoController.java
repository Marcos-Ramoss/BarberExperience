package com.barberexperience.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberexperience.application.gattewars.agendamento.*;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.infrastructure.persistence.mappers.AgendamentoResponseMapper;
import com.barberexperience.presentation.dtos.AgendamentoResponse;
import com.barberexperience.presentation.dtos.CriarAgendamentoRequest;
import com.barberexperience.presentation.dtos.AtualizarAgendamentoRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamento")
@SecurityRequirement(name = "bearerAuth")
public class AgendamentoController {
    
    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase;
    private final ListarAgendamentosUseCase listarAgendamentosUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final ExcluirAgendamentoUseCase excluirAgendamentoUseCase;
    
    @PostMapping
    @Operation(summary = "Criar um novo agendamento")
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody CriarAgendamentoRequest request) {
        AgendamentoDomain agendamento = criarAgendamentoUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AgendamentoResponseMapper.toDto(agendamento));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID")
    public ResponseEntity<AgendamentoResponse> buscarAgendamentoPorId(@PathVariable Long id) {
        return buscarAgendamentoPorIdUseCase.execute(id)
                .map(agendamento -> ResponseEntity.ok(AgendamentoResponseMapper.toDto(agendamento)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os agendamentos")
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamentos() {
        List<AgendamentoDomain> agendamentos = listarAgendamentosUseCase.execute();
        List<AgendamentoResponse> response = agendamentos.stream()
                .map(AgendamentoResponseMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamento(@PathVariable Long id, @RequestBody AtualizarAgendamentoRequest request) {
        AgendamentoDomain agendamento = atualizarAgendamentoUseCase.execute(id, request);
        return ResponseEntity.ok(AgendamentoResponseMapper.toDto(agendamento));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir agendamento")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id) {
        excluirAgendamentoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
} 