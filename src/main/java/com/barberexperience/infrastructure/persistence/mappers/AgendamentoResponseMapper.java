package com.barberexperience.infrastructure.persistence.mappers;

import java.util.stream.Collectors;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.presentation.dtos.AgendamentoResponse;


public class AgendamentoResponseMapper {
    public static AgendamentoResponse toDto(AgendamentoDomain agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                ClienteDtoMapper.toDto(agendamento.getCliente()),
                ProfissionalDtoMapper.toDto(agendamento.getProfissional()),
                agendamento.getServicos().stream()
                        .map(ServicoDtoMapper::toDto)
                        .collect(Collectors.toList()),
                agendamento.getDataHora(),
                agendamento.getStatus().name()
        );
    }
} 