package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.presentation.dtos.EnderecoDto;

public class EnderecoDtoMapper {
    public static EnderecoDto toDto(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDto(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
} 