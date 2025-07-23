package com.barberexperience.infrastructure.persistence.mappers;

import java.util.List;
import org.springframework.stereotype.Component;

import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.infrastructure.persistence.entities.AgendamentoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendamentoMapper {
    
    private final ClienteMapper clienteMapper;
    private final ProfissionalMapper profissionalMapper;
    private final BarbeariaMapper barbeariaMapper;
    
    public AgendamentoDomain toDomain(AgendamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return AgendamentoDomain.builder()
                .id(entity.getId())
                .cliente(clienteMapper.toDomain(entity.getCliente()))
                .profissional(profissionalMapper.toDomain(entity.getProfissional()))
                .barbearia(barbeariaMapper.toDomain(entity.getBarbearia()))
                .dataHora(entity.getDataHora())
                .status(entity.getStatus())
                .observacoes(entity.getObservacoes())
                .build();
    }
    
    public AgendamentoEntity toEntity(AgendamentoDomain agendamento) {
        if (agendamento == null) {
            return null;
        }
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setId(agendamento.getId());
        entity.setCliente(clienteMapper.toEntity(agendamento.getCliente()));
        entity.setProfissional(profissionalMapper.toEntity(agendamento.getProfissional()));
        // Ajuste crítico: setar o objeto BarbeariaEntity com o ID
        if (agendamento.getBarbearia() != null && agendamento.getBarbearia().getId() != null) {
            var barbeariaEntity = new com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity();
            barbeariaEntity.setId(agendamento.getBarbearia().getId());
            entity.setBarbearia(barbeariaEntity);
        } else {
            entity.setBarbearia(null);
        }
        entity.setDataHora(agendamento.getDataHora());
        entity.setStatus(agendamento.getStatus());
        entity.setObservacoes(agendamento.getObservacoes());
        return entity;
    }
} 