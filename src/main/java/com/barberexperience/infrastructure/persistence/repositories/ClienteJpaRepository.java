package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.infrastructure.persistence.entities.ClienteEntity;
import com.barberexperience.infrastructure.persistence.mappers.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClienteJpaRepository implements ClienteRepository {
    
    private final ClienteSpringDataRepository springDataRepository;
    private final ClienteMapper mapper;
    
    @Override
    public ClienteDomain save(ClienteDomain cliente) {
        if (cliente.getId() != null) {
            // Atualização: buscar entidade existente e atualizar
            Optional<ClienteEntity> existingEntityOpt = springDataRepository.findById(cliente.getId());
            if (existingEntityOpt.isPresent()) {
                ClienteEntity existingEntity = existingEntityOpt.get();
                ClienteEntity updatedEntity = mapper.updateEntity(cliente, existingEntity);
                ClienteEntity savedEntity = springDataRepository.save(updatedEntity);
                return mapper.toDomain(savedEntity);
            }
        }
        
        // Criação: criar nova entidade
        ClienteEntity entity = mapper.toEntity(cliente);
        ClienteEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<ClienteDomain> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<ClienteDomain> findAll() {
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