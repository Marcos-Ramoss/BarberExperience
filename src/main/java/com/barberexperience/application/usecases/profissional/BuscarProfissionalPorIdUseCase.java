package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.entities.Profissional;

import java.util.Optional;

public interface BuscarProfissionalPorIdUseCase {
    Optional<Profissional> execute(Long id);
} 