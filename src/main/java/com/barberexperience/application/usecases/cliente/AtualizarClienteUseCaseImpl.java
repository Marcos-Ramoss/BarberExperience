package com.barberexperience.application.usecases.cliente;

import com.barberexperience.application.gattewars.cliente.AtualizarClienteUseCase;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.presentation.dtos.AtualizarClienteRequest;
import com.barberexperience.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtualizarClienteUseCaseImpl implements AtualizarClienteUseCase {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public ClienteDomain execute(Long id, AtualizarClienteRequest request) {
        validarDadosCliente(request);
        
        ClienteDomain cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
        
        // Atualizar campos básicos
        if (request.nome() != null) {
            cliente.atualizarNome(request.nome());
        }
        
        if (request.cpf() != null) {
            cliente.atualizarCpf(request.cpf());
        }
        
        if (request.telefone() != null) {
            cliente.atualizarTelefone(request.telefone());
        }
        
        if (request.dataNascimento() != null) {
            LocalDateTime dataNascimento = DateUtils.parseFlexibleDate(request.dataNascimento())
                    .atStartOfDay();
            cliente.atualizarDataNascimento(dataNascimento);
        }
        
        return clienteRepository.save(cliente);
    }
    
    private void validarDadosCliente(AtualizarClienteRequest request) {
        if (request.nome() != null && request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }
        
        if (request.cpf() != null && request.cpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode estar vazio");
        }
        
        if (request.dataNascimento() != null) {
            try {
                DateUtils.parseFlexibleDate(request.dataNascimento());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy ou yyyy-MM-dd");
            }
        }
    }
} 