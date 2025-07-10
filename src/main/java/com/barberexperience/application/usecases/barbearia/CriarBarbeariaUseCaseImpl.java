package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.application.gattewars.barbearia.CriarBarbeariaUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import com.barberexperience.presentation.dtos.CriarBarbeariaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CriarBarbeariaUseCaseImpl implements CriarBarbeariaUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public BarbeariaDomain execute(CriarBarbeariaRequest request) {
       
        validarDadosBarbearia(request);
        
        NomeBarbearia nome = new NomeBarbearia(request.nome());
        Endereco endereco = new Endereco(
            request.rua(),
            request.numero(),
            request.bairro(),
            request.cidade(),
            request.estado(),
            request.cep()
        );
        HorarioFuncionamento horario = new HorarioFuncionamento(
            LocalTime.parse(request.horaAbertura()),
            LocalTime.parse(request.horaFechamento())
        );
        
        BarbeariaDomain barbearia = BarbeariaDomain.builder()
                .nome(nome)
                .build();

        barbearia.atualizarCnpj(request.cnpj());
        barbearia.atualizarTelefone(request.telefone());
        barbearia.atualizarEmail(request.email());
        barbearia.atualizarEndereco(endereco);
        barbearia.atualizarHorarioFuncionamento(horario);
        return barbeariaRepository.save(barbearia);
    }
    
    private void validarDadosBarbearia(CriarBarbeariaRequest request) {
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da barbearia é obrigatório");
        }
        
        if (request.cnpj() == null || request.cnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }
        
        if (request.rua() == null || request.rua().trim().isEmpty()) {
            throw new IllegalArgumentException("Rua é obrigatória");
        }
        
        if (request.cidade() == null || request.cidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        
        if (request.estado() == null || request.estado().trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        
        if (request.cep() == null || request.cep().trim().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }
        
        if (request.horaAbertura() == null || request.horaFechamento() == null) {
            throw new IllegalArgumentException("Horário de funcionamento é obrigatório");
        }
    }
} 