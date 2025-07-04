package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteSpringDataRepository extends JpaRepository<ClienteEntity, Long> {
    
    Optional<ClienteEntity> findByCpf(String cpf);
    
    Optional<ClienteEntity> findByEmail(String email);
    
    List<ClienteEntity> findByAtivo(Boolean ativo);
    
    boolean existsByCpf(String cpf);
    
    boolean existsByEmail(String email);
} 