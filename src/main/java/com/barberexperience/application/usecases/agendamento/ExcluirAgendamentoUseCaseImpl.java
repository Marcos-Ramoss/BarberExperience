package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.application.gattewars.agendamento.ExcluirAgendamentoUseCase;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirAgendamentoUseCaseImpl implements ExcluirAgendamentoUseCase {
    
    private final AgendamentoRepository agendamentoRepository;
    
    @Override
    public void execute(Long id) {
        if (agendamentoRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Agendamento não encontrado com ID: " + id);
        }
        agendamentoRepository.deleteById(id);
    }
} 