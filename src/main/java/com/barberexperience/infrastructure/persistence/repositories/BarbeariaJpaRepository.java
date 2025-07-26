package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.BarbeariaDomain;
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
    public BarbeariaDomain save(BarbeariaDomain barbearia) {
        if (barbearia.getId() != null) {
            // Atualização: buscar entidade existente e atualizar
            Optional<BarbeariaEntity> existingEntityOpt = springDataRepository.findById(barbearia.getId());
            if (existingEntityOpt.isPresent()) {
                BarbeariaEntity existingEntity = existingEntityOpt.get();
                BarbeariaEntity updatedEntity = mapper.updateEntity(barbearia, existingEntity);
                BarbeariaEntity savedEntity = springDataRepository.save(updatedEntity);
                return mapper.toDomain(savedEntity);
            }
        }
        
        // Criação: criar nova entidade
        BarbeariaEntity entity = mapper.toEntity(barbearia);
        BarbeariaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<BarbeariaDomain> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<BarbeariaDomain> findAll() {
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