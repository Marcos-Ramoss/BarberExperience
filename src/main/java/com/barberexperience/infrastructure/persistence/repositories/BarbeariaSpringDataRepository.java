package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.infrastructure.persistence.entities.BarbeariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarbeariaSpringDataRepository extends JpaRepository<BarbeariaEntity, Long> {
    
    Optional<BarbeariaEntity> findByCnpj(String cnpj);
    
    List<BarbeariaEntity> findByAtivo(Boolean ativo);
    
    boolean existsByCnpj(String cnpj);
} 