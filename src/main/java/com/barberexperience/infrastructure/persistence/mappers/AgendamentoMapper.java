package com.barberexperience.infrastructure.persistence.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.infrastructure.persistence.entities.AgendamentoEntity;
import com.barberexperience.infrastructure.persistence.entities.ServicoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendamentoMapper {
    
    private final ClienteMapper clienteMapper;
    private final ProfissionalMapper profissionalMapper;
    private final BarbeariaMapper barbeariaMapper;
    private final ServicoMapper servicoMapper;
    
    public AgendamentoDomain toDomain(AgendamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        List<com.barberexperience.domain.ServicoDomain> servicos = null;
        if (entity.getServicos() != null) {
            servicos = entity.getServicos().stream()
                    .map(servicoMapper::toDomain)
                    .collect(Collectors.toList());
        }
        
        return AgendamentoDomain.builder()
                .id(entity.getId())
                .cliente(clienteMapper.toDomain(entity.getCliente()))
                .profissional(profissionalMapper.toDomain(entity.getProfissional()))
                .barbearia(barbeariaMapper.toDomain(entity.getBarbearia()))
                .servicos(servicos)
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
        
        // Mapear serviços - criar referências para entidades existentes
        if (agendamento.getServicos() != null) {
            List<ServicoEntity> servicos = agendamento.getServicos().stream()
                    .map(servico -> {
                        // Criar apenas uma referência para a entidade existente
                        ServicoEntity servicoEntity = new ServicoEntity();
                        servicoEntity.setId(servico.getId());
                        return servicoEntity;
                    })
                    .collect(Collectors.toList());
            entity.setServicos(servicos);
        }
        
        entity.setDataHora(agendamento.getDataHora());
        entity.setStatus(agendamento.getStatus());
        entity.setObservacoes(agendamento.getObservacoes());
        
        // IMPORTANTE: Não definir dataCriacao e dataAtualizacao aqui
        // Deixar que o @PrePersist e @PreUpdate do AgendamentoEntity cuidem disso
        
        return entity;
    }
    
    /**
     * Método para atualizar uma entidade existente preservando campos de auditoria
     */
    public AgendamentoEntity updateEntity(AgendamentoDomain agendamento, AgendamentoEntity existingEntity) {
        if (agendamento == null || existingEntity == null) {
            return null;
        }
        
        // Preservar campos de auditoria existentes
        existingEntity.setCliente(clienteMapper.toEntity(agendamento.getCliente()));
        existingEntity.setProfissional(profissionalMapper.toEntity(agendamento.getProfissional()));
        
        // Atualizar barbearia se fornecida
        if (agendamento.getBarbearia() != null && agendamento.getBarbearia().getId() != null) {
            var barbeariaEntity = new com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity();
            barbeariaEntity.setId(agendamento.getBarbearia().getId());
            existingEntity.setBarbearia(barbeariaEntity);
        }
        
        // Atualizar serviços se fornecidos - criar referências para entidades existentes
        if (agendamento.getServicos() != null) {
            List<ServicoEntity> servicos = agendamento.getServicos().stream()
                    .map(servico -> {
                        // Criar apenas uma referência para a entidade existente
                        ServicoEntity servicoEntity = new ServicoEntity();
                        servicoEntity.setId(servico.getId());
                        return servicoEntity;
                    })
                    .collect(Collectors.toList());
            existingEntity.setServicos(servicos);
        }
        
        existingEntity.setDataHora(agendamento.getDataHora());
        existingEntity.setStatus(agendamento.getStatus());
        existingEntity.setObservacoes(agendamento.getObservacoes());
        
        // dataCriacao permanece inalterada
        // dataAtualizacao será atualizada automaticamente pelo @PreUpdate
        
        return existingEntity;
    }
} 