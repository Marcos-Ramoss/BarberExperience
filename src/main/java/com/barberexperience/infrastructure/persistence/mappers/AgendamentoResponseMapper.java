package com.barberexperience.infrastructure.persistence.mappers;

import java.util.Collections;
import java.util.stream.Collectors;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.presentation.dtos.AgendamentoResponse;


public class AgendamentoResponseMapper {
    public static AgendamentoResponse toDto(AgendamentoDomain agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                ClienteDtoMapper.toDto(agendamento.getCliente()),
                ProfissionalDtoMapper.toDto(agendamento.getProfissional()),
                agendamento.getServicos() != null ? 
                    agendamento.getServicos().stream()
                        .map(ServicoDtoMapper::toDto)
                        .collect(Collectors.toList()) : 
                    Collections.emptyList(),
                agendamento.getDataHora(),
                agendamento.getStatus().name()
        );
    }
} 