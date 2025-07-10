package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.presentation.dtos.ProfissionalDto;

public class ProfissionalDtoMapper {
    public static ProfissionalDto toDto(ProfissionalDomain profissional) {
        if (profissional == null) {
            return null;
        }
        return new ProfissionalDto(
            profissional.getId(),
            profissional.getNome()
        );
    }
} 