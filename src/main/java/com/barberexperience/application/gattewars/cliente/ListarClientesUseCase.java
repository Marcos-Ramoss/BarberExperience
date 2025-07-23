package com.barberexperience.application.gattewars.cliente;

import com.barberexperience.domain.ClienteDomain;
import java.util.List;

public interface ListarClientesUseCase {
    List<ClienteDomain> execute();
} 