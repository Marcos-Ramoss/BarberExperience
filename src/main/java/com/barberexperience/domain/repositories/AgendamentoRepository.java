package com.barberexperience.domain.repositories;

import java.util.Optional;

import com.barberexperience.domain.AgendamentoDomain;

import java.util.List;

public interface AgendamentoRepository {
    Optional<AgendamentoDomain> findById(Long id);
    List<AgendamentoDomain> findAll();
    AgendamentoDomain save(AgendamentoDomain agendamento);
    void deleteById(Long id);
} 