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