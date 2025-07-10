package com.barberexperience.application.gattewars.barbearia;

import java.util.Optional;

import com.barberexperience.domain.BarbeariaDomain;

public interface BuscarBarbeariaPorIdUseCase {
    Optional<BarbeariaDomain> execute(Long id);
} 