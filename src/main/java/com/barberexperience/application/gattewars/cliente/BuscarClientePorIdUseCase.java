package com.barberexperience.application.gattewars.cliente;

import com.barberexperience.domain.ClienteDomain;
import java.util.Optional;

public interface BuscarClientePorIdUseCase {
    Optional<ClienteDomain> execute(Long id);
} 