package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.entities.Profissional;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProfissionalUseCaseImpl implements CriarProfissionalUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    
    @Override
    public Profissional execute(CriarProfissionalRequest request) {
        // Validações de negócio
        validarDadosProfissional(request);
        
        // Criação da entidade de domínio
        Profissional profissional = Profissional.builder()
                .nome(request.getNome())
                .especialidades(request.getEspecialidades())
                .build();
        
        // Persistência via interface (não depende de implementação)
        return profissionalRepository.save(profissional);
    }
    
    private void validarDadosProfissional(CriarProfissionalRequest request) {
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }
        
        if (request.getEspecialidades() == null || request.getEspecialidades().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos uma especialidade é obrigatória");
        }
    }
} 