package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.domain.entities.Barbearia;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CriarBarbeariaUseCaseImpl implements CriarBarbeariaUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public Barbearia execute(CriarBarbeariaRequest request) {
        // Validações de negócio
        validarDadosBarbearia(request);
        
        // Criação dos value objects
        NomeBarbearia nome = new NomeBarbearia(request.getNome());
        Endereco endereco = new Endereco(
            request.getRua(),
            request.getNumero(),
            request.getBairro(),
            request.getCidade(),
            request.getEstado(),
            request.getCep()
        );
        HorarioFuncionamento horario = new HorarioFuncionamento(
            LocalTime.parse(request.getHoraAbertura()),
            LocalTime.parse(request.getHoraFechamento())
        );
        
        // Criação da entidade de domínio
        Barbearia barbearia = Barbearia.builder()
                .nome(nome)
                .endereco(endereco)
                .horarioFuncionamento(horario)
                .build();
        
        // Persistência via interface (não depende de implementação)
        return barbeariaRepository.save(barbearia);
    }
    
    private void validarDadosBarbearia(CriarBarbeariaRequest request) {
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da barbearia é obrigatório");
        }
        
        if (request.getRua() == null || request.getRua().trim().isEmpty()) {
            throw new IllegalArgumentException("Rua é obrigatória");
        }
        
        if (request.getCidade() == null || request.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        
        if (request.getEstado() == null || request.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        
        if (request.getCep() == null || request.getCep().trim().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }
        
        if (request.getHoraAbertura() == null || request.getHoraFechamento() == null) {
            throw new IllegalArgumentException("Horário de funcionamento é obrigatório");
        }
    }
} 