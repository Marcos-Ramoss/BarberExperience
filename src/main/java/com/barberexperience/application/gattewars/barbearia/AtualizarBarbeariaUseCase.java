package com.barberexperience.application.gattewars.barbearia;

import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.presentation.dtos.AtualizarBarbeariaRequest;

public interface AtualizarBarbeariaUseCase {
    BarbeariaDomain execute(Long id, AtualizarBarbeariaRequest request);
} 