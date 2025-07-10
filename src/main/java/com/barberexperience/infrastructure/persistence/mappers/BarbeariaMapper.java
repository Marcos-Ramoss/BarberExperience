package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity;

@Component
public class BarbeariaMapper {
    
    public BarbeariaDomain toDomain(BarbeariaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return BarbeariaDomain.builder()
                .id(entity.getId())
                .nome(new NomeBarbearia(entity.getNome()))
                .cnpj(entity.getCnpj())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .endereco(entity.getEndereco())
                .horarioFuncionamento(entity.getHorarioFuncionamento())
                .build();
    }
    
    public BarbeariaEntity toEntity(BarbeariaDomain barbearia) {
        if (barbearia == null) {
            return null;
        }
        
        BarbeariaEntity entity = new BarbeariaEntity();
        entity.setId(barbearia.getId());
        entity.setNome(barbearia.getNome().getValue());
        entity.setCnpj(barbearia.getCnpj());
        entity.setTelefone(barbearia.getTelefone());
        entity.setEmail(barbearia.getEmail());
        entity.setEndereco(barbearia.getEndereco());
        entity.setHorarioFuncionamento(barbearia.getHorarioFuncionamento());
        
        return entity;
    }
} 