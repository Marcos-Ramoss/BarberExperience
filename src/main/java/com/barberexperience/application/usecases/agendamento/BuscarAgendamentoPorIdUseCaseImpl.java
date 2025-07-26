package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.application.gattewars.agendamento.BuscarAgendamentoPorIdUseCase;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarAgendamentoPorIdUseCaseImpl implements BuscarAgendamentoPorIdUseCase {
    
    private final AgendamentoRepository agendamentoRepository;
    
    @Override
    public Optional<AgendamentoDomain> execute(Long id) {
        return agendamentoRepository.findById(id);
    }
} 