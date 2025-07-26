package com.barberexperience.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberexperience.application.gattewars.agendamento.CriarAgendamentoUseCase;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.infrastructure.persistence.mappers.AgendamentoResponseMapper;
import com.barberexperience.presentation.dtos.AgendamentoResponse;
import com.barberexperience.presentation.dtos.CriarAgendamentoRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamento")
@SecurityRequirement(name = "bearerAuth")
public class AgendamentoController {
    
    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    
    @PostMapping
    @Operation(summary = "Criar um novo agendamento")
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody CriarAgendamentoRequest request) {
        AgendamentoDomain agendamento = criarAgendamentoUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AgendamentoResponseMapper.toDto(agendamento));
    }
} 