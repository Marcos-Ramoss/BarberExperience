package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.entities.Profissional;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProfissionaisUseCaseImpl implements ListarProfissionaisUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    
    @Override
    public List<Profissional> execute() {
        return profissionalRepository.findAll();
    }
} 