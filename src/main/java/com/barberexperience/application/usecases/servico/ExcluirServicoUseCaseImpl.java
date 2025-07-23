package com.barberexperience.application.usecases.servico;

import com.barberexperience.application.gattewars.servico.ExcluirServicoUseCase;
import com.barberexperience.domain.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirServicoUseCaseImpl implements ExcluirServicoUseCase {
    
    private final ServicoRepository servicoRepository;
    
    @Override
    public void execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do serviço é obrigatório");
        }
        
        if (!servicoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Serviço não encontrado");
        }
        
        servicoRepository.deleteById(id);
    }
} 