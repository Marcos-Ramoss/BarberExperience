package com.barberexperience.domain.repositories;

import com.barberexperience.domain.entities.Agendamento;
import java.util.Optional;
import java.util.List;

public interface AgendamentoRepository {
    Optional<Agendamento> findById(Long id);
    List<Agendamento> findAll();
    Agendamento save(Agendamento agendamento);
    void deleteById(Long id);
} 