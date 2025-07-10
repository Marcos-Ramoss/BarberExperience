package com.barberexperience.application.usecases.profissional;

import com.barberexperience.application.gattewars.profissional.ListarProfissionaisUseCase;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProfissionaisUseCaseImpl implements ListarProfissionaisUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    
    @Override
    public List<ProfissionalDomain> execute() {
        return profissionalRepository.findAll();
    }
} 