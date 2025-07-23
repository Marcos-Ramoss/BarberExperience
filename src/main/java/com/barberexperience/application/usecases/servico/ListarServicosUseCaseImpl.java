package com.barberexperience.application.usecases.servico;

import com.barberexperience.application.gattewars.servico.ListarServicosUseCase;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarServicosUseCaseImpl implements ListarServicosUseCase {
    
    private final ServicoRepository servicoRepository;
    
    @Override
    public List<ServicoDomain> execute() {
        return servicoRepository.findAll();
    }
} 