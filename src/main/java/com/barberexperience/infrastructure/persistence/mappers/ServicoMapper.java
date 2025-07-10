package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.infrastructure.persistence.entities.ServicoEntity;

@Component
public class ServicoMapper {
    
    public ServicoDomain toDomain(ServicoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return ServicoDomain.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .duracaoMinutos(entity.getDuracaoMinutos())
                .build();
    }
    
    public ServicoEntity toEntity(ServicoDomain servico) {
        if (servico == null) {
            return null;
        }
        
        ServicoEntity entity = new ServicoEntity();
        entity.setId(servico.getId());
        entity.setNome(servico.getNome());
        entity.setPreco(servico.getPreco());
        entity.setDuracaoMinutos(servico.getDuracaoMinutos());
        
        return entity;
    }
} 