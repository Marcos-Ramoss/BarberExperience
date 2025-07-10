package com.barberexperience.application.gattewars.agendamento;

import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.presentation.dtos.CriarAgendamentoRequest;

public interface CriarAgendamentoUseCase {
    AgendamentoDomain execute(CriarAgendamentoRequest request);
} 