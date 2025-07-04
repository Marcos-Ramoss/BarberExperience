package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.infrastructure.persistence.entities.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalSpringDataRepository extends JpaRepository<ProfissionalEntity, Long> {
    
    Optional<ProfissionalEntity> findByCpf(String cpf);
    
    List<ProfissionalEntity> findByBarbeariaId(Long barbeariaId);
    
    List<ProfissionalEntity> findByAtivo(Boolean ativo);
    
    List<ProfissionalEntity> findByBarbeariaIdAndAtivo(Long barbeariaId, Boolean ativo);
    
    boolean existsByCpf(String cpf);
} 