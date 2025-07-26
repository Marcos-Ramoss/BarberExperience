package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import com.barberexperience.infrastructure.persistence.entities.AgendamentoEntity;
import com.barberexperience.infrastructure.persistence.mappers.AgendamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AgendamentoJpaRepository implements AgendamentoRepository {
    
    private final AgendamentoSpringDataRepository springDataRepository;
    private final AgendamentoMapper mapper;
    
    @Override
    public AgendamentoDomain save(AgendamentoDomain agendamento) {
        if (agendamento.getId() != null) {
            // Atualização: buscar entidade existente e atualizar
            Optional<AgendamentoEntity> existingEntityOpt = springDataRepository.findById(agendamento.getId());
            if (existingEntityOpt.isPresent()) {
                AgendamentoEntity existingEntity = existingEntityOpt.get();
                AgendamentoEntity updatedEntity = mapper.updateEntity(agendamento, existingEntity);
                AgendamentoEntity savedEntity = springDataRepository.save(updatedEntity);
                return mapper.toDomain(savedEntity);
            }
        }
        
        // Criação: criar nova entidade
        AgendamentoEntity entity = mapper.toEntity(agendamento);
        AgendamentoEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<AgendamentoDomain> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<AgendamentoDomain> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
} 