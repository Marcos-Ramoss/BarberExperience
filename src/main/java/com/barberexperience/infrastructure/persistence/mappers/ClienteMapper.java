package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    
    public ClienteDomain toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return ClienteDomain.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
    
    public ClienteEntity toEntity(ClienteDomain cliente) {
        if (cliente == null) {
            return null;
        }
        
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNome(cliente.getNome());
        entity.setEmail(cliente.getEmail());
        
        return entity;
    }
} 