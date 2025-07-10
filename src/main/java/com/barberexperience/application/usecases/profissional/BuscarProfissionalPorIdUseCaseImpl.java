package com.barberexperience.application.usecases.profissional;

import com.barberexperience.application.gattewars.profissional.BuscarProfissionalPorIdUseCase;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarProfissionalPorIdUseCaseImpl implements BuscarProfissionalPorIdUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    
    @Override
    public Optional<ProfissionalDomain> execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do profissional é obrigatório");
        }
        
        return profissionalRepository.findById(id);
    }
} 