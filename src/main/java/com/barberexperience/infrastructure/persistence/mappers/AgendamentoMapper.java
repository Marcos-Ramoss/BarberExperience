package com.barberexperience.infrastructure.persistence.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import com.barberexperience.domain.entities.Agendamento;
import com.barberexperience.infrastructure.persistence.entities.AgendamentoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendamentoMapper {
    
    private final ClienteMapper clienteMapper;
    private final ProfissionalMapper profissionalMapper;
    private final ServicoMapper servicoMapper;
    
    public Agendamento toDomain(AgendamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Agendamento.builder()
                .id(entity.getId())
                .cliente(clienteMapper.toDomain(entity.getCliente()))
                .profissional(profissionalMapper.toDomain(entity.getProfissional()))
                .servicos(List.of(servicoMapper.toDomain(entity.getServico()))) // Simplificação: um agendamento tem um serviço
                .horario(entity.getDataHora())
                .status(entity.getStatus())
                .build();
    }
    
    public AgendamentoEntity toEntity(Agendamento agendamento) {
        if (agendamento == null) {
            return null;
        }
        
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setId(agendamento.getId());
        entity.setCliente(clienteMapper.toEntity(agendamento.getCliente()));
        entity.setProfissional(profissionalMapper.toEntity(agendamento.getProfissional()));
        
        // Simplificação: pega o primeiro serviço da lista
        if (agendamento.getServicos() != null && !agendamento.getServicos().isEmpty()) {
            entity.setServico(servicoMapper.toEntity(agendamento.getServicos().get(0)));
        }
        
        entity.setDataHora(agendamento.getHorario());
        entity.setStatus(agendamento.getStatus());
        
        return entity;
    }
} 