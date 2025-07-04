package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.entities.Servico;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.infrastructure.persistence.entities.ServicoEntity;
import com.barberexperience.infrastructure.persistence.mappers.ServicoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ServicoJpaRepository implements ServicoRepository {
    
    private final ServicoSpringDataRepository springDataRepository;
    private final ServicoMapper mapper;
    
    @Override
    public Servico save(Servico servico) {
        ServicoEntity entity = mapper.toEntity(servico);
        ServicoEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Servico> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Servico> findAll() {
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