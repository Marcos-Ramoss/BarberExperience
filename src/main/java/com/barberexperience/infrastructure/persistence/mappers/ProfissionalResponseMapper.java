package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.valueobjects.Especialidade;
import com.barberexperience.presentation.dtos.ProfissionalResponse;
import java.util.stream.Collectors;

public class ProfissionalResponseMapper {
    public static ProfissionalResponse toDto(ProfissionalDomain profissional) {
        if (profissional == null) {
            return null;
        }
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getCpf(),
                profissional.getTelefone(),
                profissional.getEmail(),
                profissional.getEspecialidades().stream()
                        .map(Especialidade::name)
                        .collect(Collectors.toList()),
                profissional.getBarbearia() != null ? profissional.getBarbearia().getId() : null
        );
    }
} 