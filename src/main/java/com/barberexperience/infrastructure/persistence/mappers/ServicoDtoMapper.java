package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.presentation.dtos.ServicoDto;

public class ServicoDtoMapper {
    public static ServicoDto toDto(ServicoDomain servico) {
        if (servico == null) {
            return null;
        }
        return new ServicoDto(
            servico.getId(),
            servico.getNome(),
            servico.getPreco().toString(),
            servico.getDuracaoMinutos()
        );
    }
} 