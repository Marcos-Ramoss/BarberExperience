package com.barberexperience.application.gattewars.servico;

import com.barberexperience.domain.ServicoDomain;
import java.util.List;

public interface ListarServicosUseCase {
    List<ServicoDomain> execute();
} 