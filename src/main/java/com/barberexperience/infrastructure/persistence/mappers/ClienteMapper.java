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
                .cpf(entity.getCpf())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
    
    public ClienteEntity toEntity(ClienteDomain cliente) {
        if (cliente == null) {
            return null;
        }
        
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNome(cliente.getNome());
        entity.setCpf(cliente.getCpf());
        entity.setTelefone(cliente.getTelefone());
        entity.setEmail(cliente.getEmail());
        entity.setDataNascimento(cliente.getDataNascimento());
        
        return entity;
    }
} 