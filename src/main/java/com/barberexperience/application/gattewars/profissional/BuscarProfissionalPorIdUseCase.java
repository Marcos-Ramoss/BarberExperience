package com.barberexperience.application.gattewars.profissional;

import java.util.Optional;

import com.barberexperience.domain.ProfissionalDomain;

public interface BuscarProfissionalPorIdUseCase {
    Optional<ProfissionalDomain> execute(Long id);
} 