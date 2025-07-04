package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import com.barberexperience.domain.entities.Barbearia;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity;

@Component
public class BarbeariaMapper {
    
    public Barbearia toDomain(BarbeariaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Barbearia.builder()
                .id(entity.getId())
                .nome(new NomeBarbearia(entity.getNome()))
                .endereco(entity.getEndereco())
                .horarioFuncionamento(entity.getHorarioFuncionamento())
                .build();
    }
    
    public BarbeariaEntity toEntity(Barbearia barbearia) {
        if (barbearia == null) {
            return null;
        }
        
        BarbeariaEntity entity = new BarbeariaEntity();
        entity.setId(barbearia.getId());
        entity.setNome(barbearia.getNome().getValue());
        entity.setEndereco(barbearia.getEndereco());
        entity.setHorarioFuncionamento(barbearia.getHorarioFuncionamento());
        
        return entity;
    }
} 