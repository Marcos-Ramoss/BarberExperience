package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.application.gattewars.barbearia.BuscarBarbeariaPorIdUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarBarbeariaPorIdUseCaseImpl implements BuscarBarbeariaPorIdUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public Optional<BarbeariaDomain> execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da barbearia é obrigatório");
        }
        
        return barbeariaRepository.findById(id);
    }
} 