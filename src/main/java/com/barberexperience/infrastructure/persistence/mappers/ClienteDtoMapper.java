package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.presentation.dtos.ClienteDto;


public class ClienteDtoMapper {
    public static ClienteDto toDto(ClienteDomain cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDto(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail()
        );
    }
} 