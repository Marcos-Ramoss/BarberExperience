package com.barberexperience.application.gattewars.agendamento;

import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.presentation.dtos.AtualizarAgendamentoRequest;

public interface AtualizarAgendamentoUseCase {
    AgendamentoDomain execute(Long id, AtualizarAgendamentoRequest request);
} 