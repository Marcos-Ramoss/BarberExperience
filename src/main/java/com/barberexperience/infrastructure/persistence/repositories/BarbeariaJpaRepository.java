package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.entities.Barbearia;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity;
import com.barberexperience.infrastructure.persistence.mappers.BarbeariaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BarbeariaJpaRepository implements BarbeariaRepository {
    
    private final BarbeariaSpringDataRepository springDataRepository;
    private final BarbeariaMapper mapper;
    
    @Override
    public Barbearia save(Barbearia barbearia) {
        BarbeariaEntity entity = mapper.toEntity(barbearia);
        BarbeariaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Barbearia> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Barbearia> findAll() {
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