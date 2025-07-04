package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.domain.entities.Agendamento;

public interface CriarAgendamentoUseCase {
    Agendamento execute(CriarAgendamentoRequest request);
} 