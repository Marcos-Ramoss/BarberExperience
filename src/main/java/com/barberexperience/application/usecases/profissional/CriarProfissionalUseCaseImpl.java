package com.barberexperience.application.usecases.profissional;

import com.barberexperience.application.gattewars.profissional.CriarProfissionalUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProfissionalUseCaseImpl implements CriarProfissionalUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public ProfissionalDomain execute(CriarProfissionalRequest request) {
        // Validações de negócio
        validarDadosProfissional(request);
        
        // Buscar a barbearia
        BarbeariaDomain barbearia = barbeariaRepository.findById(request.barbeariaId())
            .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada com ID: " + request.barbeariaId()));
        
        // Criação da entidade de domínio
        ProfissionalDomain profissional = ProfissionalDomain.builder().build();
        profissional.atualizarNome(request.nome());
        profissional.atualizarCpf(request.cpf());
        profissional.atualizarTelefone(request.telefone());
        profissional.atualizarEmail(request.email());
        profissional.associarBarbearia(barbearia);
        request.especialidades().forEach(profissional::adicionarEspecialidade);
        
        // Persistência via interface (não depende de implementação)
        return profissionalRepository.save(profissional);
    }
    
    private void validarDadosProfissional(CriarProfissionalRequest request) {
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }
        
        if (request.cpf() == null || request.cpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do profissional é obrigatório");
        }
        
        if (request.especialidades() == null || request.especialidades().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos uma especialidade é obrigatória");
        }
        
        if (request.barbeariaId() == null) {
            throw new IllegalArgumentException("ID da barbearia é obrigatório");
        }
    }
} 