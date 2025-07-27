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
            servico.getNome() != null ? servico.getNome() : "",
            servico.getPreco() != null ? servico.getPreco().toString() : "0.00",
            servico.getDuracaoMinutos() != null ? servico.getDuracaoMinutos() : 0
        );
    }
} 