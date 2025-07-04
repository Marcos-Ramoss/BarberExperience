package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.entities.Profissional;

public interface CriarProfissionalUseCase {
    Profissional execute(CriarProfissionalRequest request);
} 