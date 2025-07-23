package com.barberexperience.application.usecases.cliente;

import com.barberexperience.application.gattewars.cliente.ExcluirClienteUseCase;
import com.barberexperience.domain.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirClienteUseCaseImpl implements ExcluirClienteUseCase {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public void execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        
        if (!clienteRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        clienteRepository.deleteById(id);
    }
} 