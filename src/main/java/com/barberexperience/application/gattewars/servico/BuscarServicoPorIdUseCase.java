package com.barberexperience.application.gattewars.servico;

import com.barberexperience.domain.ServicoDomain;
import java.util.Optional;

public interface BuscarServicoPorIdUseCase {
    Optional<ServicoDomain> execute(Long id);
} 