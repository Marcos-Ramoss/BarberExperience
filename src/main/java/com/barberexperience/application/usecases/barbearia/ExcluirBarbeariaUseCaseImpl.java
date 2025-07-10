package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.application.gattewars.barbearia.ExcluirBarbeariaUseCase;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirBarbeariaUseCaseImpl implements ExcluirBarbeariaUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public void execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da barbearia é obrigatório");
        }
        
        // Verifica se a barbearia existe antes de excluir
        if (!barbeariaRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Barbearia não encontrada");
        }
        
        barbeariaRepository.deleteById(id);
    }
} 