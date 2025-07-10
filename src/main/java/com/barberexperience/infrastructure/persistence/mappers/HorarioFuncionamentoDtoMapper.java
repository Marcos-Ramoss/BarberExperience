package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import com.barberexperience.presentation.dtos.HorarioFuncionamentoDto;

public class HorarioFuncionamentoDtoMapper {
    public static HorarioFuncionamentoDto toDto(HorarioFuncionamento horario) {
        if (horario == null) {
            return null;
        }
        return new HorarioFuncionamentoDto(
            horario.getAbertura().toString(),
            horario.getFechamento().toString()
        );
    }
} 