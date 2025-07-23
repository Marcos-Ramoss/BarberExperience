package com.barberexperience.application.usecases.servico;

import com.barberexperience.application.gattewars.servico.BuscarServicoPorIdUseCase;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarServicoPorIdUseCaseImpl implements BuscarServicoPorIdUseCase {
    
    private final ServicoRepository servicoRepository;
    
    @Override
    public Optional<ServicoDomain> execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do serviço é obrigatório");
        }
        return servicoRepository.findById(id);
    }
} 