package com.barberexperience.application.gattewars.cliente;

import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.presentation.dtos.AtualizarClienteRequest;

public interface AtualizarClienteUseCase {
    ClienteDomain execute(Long id, AtualizarClienteRequest request);
} 