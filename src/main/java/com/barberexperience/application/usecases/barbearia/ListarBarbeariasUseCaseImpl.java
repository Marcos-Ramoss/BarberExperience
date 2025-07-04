package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.domain.entities.Barbearia;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarBarbeariasUseCaseImpl implements ListarBarbeariasUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public List<Barbearia> execute() {
        return barbeariaRepository.findAll();
    }
} 