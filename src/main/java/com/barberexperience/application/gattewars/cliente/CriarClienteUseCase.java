package com.barberexperience.application.gattewars.cliente;

import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.presentation.dtos.CriarClienteRequest;

public interface CriarClienteUseCase {
    ClienteDomain execute(CriarClienteRequest request);
} 