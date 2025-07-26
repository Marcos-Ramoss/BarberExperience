package com.barberexperience.application.gattewars.agendamento;

import com.barberexperience.domain.AgendamentoDomain;
import java.util.List;

public interface ListarAgendamentosUseCase {
    List<AgendamentoDomain> execute();
} 