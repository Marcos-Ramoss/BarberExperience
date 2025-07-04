package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirProfissionalUseCaseImpl implements ExcluirProfissionalUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    
    @Override
    public void execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do profissional é obrigatório");
        }
        
        // Verifica se o profissional existe antes de excluir
        if (!profissionalRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Profissional não encontrado");
        }
        
        profissionalRepository.deleteById(id);
    }
} 