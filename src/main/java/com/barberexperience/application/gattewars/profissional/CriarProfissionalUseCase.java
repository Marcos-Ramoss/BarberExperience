package com.barberexperience.application.gattewars.profissional;

import com.barberexperience.application.usecases.profissional.CriarProfissionalRequest;
import com.barberexperience.domain.ProfissionalDomain;

public interface CriarProfissionalUseCase {
    ProfissionalDomain execute(CriarProfissionalRequest request);
} 