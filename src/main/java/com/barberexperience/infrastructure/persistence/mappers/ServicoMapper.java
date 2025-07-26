package com.barberexperience.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity;
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
        .descricao(entity.getDescricao())
        .preco(entity.getPreco())
        .duracaoMinutos(entity.getDuracaoMinutos())
        .barbearia(entity.getBarbearia() != null
            ? BarbeariaDomain.builder().id(entity.getBarbearia().getId()).build()
            : null)
        .build();
    }
    
    public ServicoEntity toEntity(ServicoDomain servico) {
        if (servico == null) {
            return null;
        }
        ServicoEntity entity = new ServicoEntity();
        entity.setId(servico.getId());
        entity.setNome(servico.getNome());
        entity.setDescricao(servico.getDescricao());
        entity.setPreco(servico.getPreco());
        entity.setDuracaoMinutos(servico.getDuracaoMinutos());
        entity.setAtivo(true);
        
        // Ajuste crítico: setar o objeto BarbeariaEntity
        if (servico.getBarbearia() != null && servico.getBarbearia().getId() != null) {
            BarbeariaEntity barbeariaEntity = new BarbeariaEntity();
            barbeariaEntity.setId(servico.getBarbearia().getId());
            entity.setBarbearia(barbeariaEntity);
        } else {
            entity.setBarbearia(null);
        }
        
        // IMPORTANTE: Não definir dataCriacao e dataAtualizacao aqui
        // Deixar que o @PrePersist e @PreUpdate do ServicoEntity cuidem disso
        return entity;
    }
    
    /**
     * Método para atualizar uma entidade existente preservando campos de auditoria
     */
    public ServicoEntity updateEntity(ServicoDomain servico, ServicoEntity existingEntity) {
        if (servico == null || existingEntity == null) {
            return null;
        }
        
        // Preservar campos de auditoria existentes
        existingEntity.setNome(servico.getNome());
        existingEntity.setDescricao(servico.getDescricao());
        existingEntity.setPreco(servico.getPreco());
        existingEntity.setDuracaoMinutos(servico.getDuracaoMinutos());
        existingEntity.setAtivo(true);
        
        // Atualizar barbearia se fornecida
        if (servico.getBarbearia() != null && servico.getBarbearia().getId() != null) {
            BarbeariaEntity barbeariaEntity = new BarbeariaEntity();
            barbeariaEntity.setId(servico.getBarbearia().getId());
            existingEntity.setBarbearia(barbeariaEntity);
        }
        
        // dataCriacao permanece inalterada
        // dataAtualizacao será atualizada automaticamente pelo @PreUpdate
        
        return existingEntity;
    }
} 