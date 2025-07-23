package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.presentation.dtos.ServicoResponse;

public class ServicoResponseMapper {
    public static ServicoResponse toDto(ServicoDomain servico) {
        return new ServicoResponse(
            servico.getId(),
            servico.getNome(),
            servico.getDescricao(),
            servico.getPreco(),
            servico.getDuracaoMinutos(),
            servico.getBarbearia() != null ? servico.getBarbearia().getId() : null
        );
    }
} 