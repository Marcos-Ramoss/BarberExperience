package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.entities.Profissional;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.infrastructure.persistence.entities.ProfissionalEntity;
import com.barberexperience.infrastructure.persistence.mappers.ProfissionalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProfissionalJpaRepository implements ProfissionalRepository {
    
    private final ProfissionalSpringDataRepository springDataRepository;
    private final ProfissionalMapper mapper;
    
    @Override
    public Profissional save(Profissional profissional) {
        ProfissionalEntity entity = mapper.toEntity(profissional);
        ProfissionalEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Profissional> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Profissional> findAll() {
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