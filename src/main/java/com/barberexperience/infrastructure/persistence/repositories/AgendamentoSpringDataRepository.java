package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.valueobjects.StatusAgendamento;
import com.barberexperience.infrastructure.persistence.entities.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoSpringDataRepository extends JpaRepository<AgendamentoEntity, Long> {
    
    List<AgendamentoEntity> findByBarbeariaId(Long barbeariaId);
    
    List<AgendamentoEntity> findByProfissionalId(Long profissionalId);
    
    List<AgendamentoEntity> findByClienteId(Long clienteId);
    
    List<AgendamentoEntity> findByStatus(StatusAgendamento status);
    
    List<AgendamentoEntity> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    
    List<AgendamentoEntity> findByBarbeariaIdAndDataHoraBetween(Long barbeariaId, LocalDateTime inicio, LocalDateTime fim);
    
    List<AgendamentoEntity> findByProfissionalIdAndDataHoraBetween(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
} 