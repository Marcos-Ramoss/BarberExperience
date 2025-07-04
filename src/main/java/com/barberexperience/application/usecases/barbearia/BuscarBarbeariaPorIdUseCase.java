package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.domain.entities.Barbearia;

import java.util.Optional;

public interface BuscarBarbeariaPorIdUseCase {
    Optional<Barbearia> execute(Long id);
} 