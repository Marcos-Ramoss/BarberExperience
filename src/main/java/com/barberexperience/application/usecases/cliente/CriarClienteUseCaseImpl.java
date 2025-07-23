package com.barberexperience.application.usecases.cliente;

import com.barberexperience.application.gattewars.cliente.CriarClienteUseCase;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.presentation.dtos.CriarClienteRequest;
import com.barberexperience.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CriarClienteUseCaseImpl implements CriarClienteUseCase {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public ClienteDomain execute(CriarClienteRequest request) {
        validarDadosCliente(request);
        
        ClienteDomain cliente = ClienteDomain.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .telefone(request.telefone())
                .email(request.email())
                .build();
        
        if (request.dataNascimento() != null && !request.dataNascimento().trim().isEmpty()) {
            LocalDate dataNascimento = DateUtils.parseFlexibleDate(request.dataNascimento());
            cliente.atualizarDataNascimento(dataNascimento.atStartOfDay());
        }
        
        return clienteRepository.save(cliente);
    }
    
    private void validarDadosCliente(CriarClienteRequest request) {
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        
        if (request.cpf() == null || request.cpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        
        if (request.email() == null || request.email().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        if (!request.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
} 