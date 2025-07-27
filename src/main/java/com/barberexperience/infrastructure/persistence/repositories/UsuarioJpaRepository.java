package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.UsuarioDomain;
import com.barberexperience.domain.repositories.UsuarioRepository;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import com.barberexperience.infrastructure.persistence.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UsuarioJpaRepository implements UsuarioRepository {
    
    private final UsuarioSpringDataRepository springDataRepository;
    private final UsuarioMapper mapper;
    
    @Override
    public UsuarioDomain save(UsuarioDomain usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<UsuarioDomain> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public Optional<UsuarioDomain> findByUsername(String username) {
        return springDataRepository.findByUsername(username)
                .map(mapper::toDomain);
    }
    
    @Override
    public Optional<UsuarioDomain> findByUsernameAndAtivoTrue(String username) {
        return springDataRepository.findByUsernameAndAtivoTrue(username)
                .map(mapper::toDomain);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return springDataRepository.existsByUsername(username);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return springDataRepository.existsByEmail(email);
    }
    
    @Override
    public List<UsuarioDomain> findAll() {
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
