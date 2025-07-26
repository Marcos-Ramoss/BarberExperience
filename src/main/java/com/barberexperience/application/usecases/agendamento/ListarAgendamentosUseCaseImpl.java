package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.application.gattewars.agendamento.ListarAgendamentosUseCase;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarAgendamentosUseCaseImpl implements ListarAgendamentosUseCase {
    
    private final AgendamentoRepository agendamentoRepository;
    
    @Override
    public List<AgendamentoDomain> execute() {
        return agendamentoRepository.findAll();
    }
} 