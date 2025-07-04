package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.domain.entities.Barbearia;

public interface CriarBarbeariaUseCase {
    Barbearia execute(CriarBarbeariaRequest request);
} 