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
    
    /**
     * Método para atualizar uma entidade existente preservando campos de auditoria
     */
    public ClienteEntity updateEntity(ClienteDomain cliente, ClienteEntity existingEntity) {
        if (cliente == null || existingEntity == null) {
            return null;
        }
        
        // Preservar campos de auditoria existentes
        existingEntity.setNome(cliente.getNome());
        existingEntity.setCpf(cliente.getCpf());
        existingEntity.setTelefone(cliente.getTelefone());
        existingEntity.setEmail(cliente.getEmail());
        existingEntity.setDataNascimento(cliente.getDataNascimento());
        
        // dataCriacao permanece inalterada
        // dataAtualizacao será atualizada automaticamente pelo @PreUpdate
        
        return existingEntity;
    }
} 