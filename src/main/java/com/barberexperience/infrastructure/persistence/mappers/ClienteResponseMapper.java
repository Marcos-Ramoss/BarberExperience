package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.presentation.dtos.ClienteResponse;

public class ClienteResponseMapper {
    public static ClienteResponse toDto(ClienteDomain cliente) {
        return new ClienteResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getTelefone(),
            cliente.getEmail(),
            cliente.getDataNascimento()
        );
    }
} 