package com.barberexperience.application.gattewars.agendamento;

import com.barberexperience.domain.AgendamentoDomain;
import java.util.Optional;

public interface BuscarAgendamentoPorIdUseCase {
    Optional<AgendamentoDomain> execute(Long id);
} 