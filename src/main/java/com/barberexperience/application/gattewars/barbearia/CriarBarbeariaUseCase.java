package com.barberexperience.application.gattewars.barbearia;

import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.presentation.dtos.CriarBarbeariaRequest;

public interface CriarBarbeariaUseCase {
    BarbeariaDomain execute(CriarBarbeariaRequest request);
} 