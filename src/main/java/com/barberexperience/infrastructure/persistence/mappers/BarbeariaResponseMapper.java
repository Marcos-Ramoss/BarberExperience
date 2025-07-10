package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.presentation.dtos.BarbeariaResponse;


public class BarbeariaResponseMapper {
    public static BarbeariaResponse toDto(BarbeariaDomain barbearia) {
        return new BarbeariaResponse(
            barbearia.getId(),
            barbearia.getNome().getValue(),
            barbearia.getCnpj(),
            barbearia.getTelefone(),
            barbearia.getEmail(),
            EnderecoDtoMapper.toDto(barbearia.getEndereco()),
            HorarioFuncionamentoDtoMapper.toDto(barbearia.getHorarioFuncionamento())
        );
    }
} 