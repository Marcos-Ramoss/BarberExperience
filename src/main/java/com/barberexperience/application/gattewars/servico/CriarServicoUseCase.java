package com.barberexperience.application.gattewars.servico;

import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.presentation.dtos.CriarServicoRequest;

public interface CriarServicoUseCase {
    ServicoDomain execute(CriarServicoRequest request);
} 