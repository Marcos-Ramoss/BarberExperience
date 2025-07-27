package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.presentation.dtos.ProfissionalResponseDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfissionalResponseDtoMapper {
    
    public ProfissionalResponseDto toDto(ProfissionalDomain profissional) {
        if (profissional == null) {
            return null;
        }
        
        return new ProfissionalResponseDto(
            profissional.getId(),
            profissional.getNome(),
            profissional.getCpf(),
            profissional.getTelefone(),
            profissional.getEmail(),
            profissional.getEspecialidades().stream()
                .map(Enum::name)
                .collect(Collectors.toList()),
            profissional.getBarbearia() != null ? profissional.getBarbearia().getId() : null
        );
    }
} 