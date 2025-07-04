package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.infrastructure.persistence.entities.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoSpringDataRepository extends JpaRepository<ServicoEntity, Long> {
    
    List<ServicoEntity> findByBarbeariaId(Long barbeariaId);
    
    List<ServicoEntity> findByAtivo(Boolean ativo);
    
    List<ServicoEntity> findByBarbeariaIdAndAtivo(Long barbeariaId, Boolean ativo);
} 