package com.barberexperience.application.gattewars.servico;

import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.presentation.dtos.AtualizarServicoRequest;

public interface AtualizarServicoUseCase {
    ServicoDomain execute(Long id, AtualizarServicoRequest request);
} 