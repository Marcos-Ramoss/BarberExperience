package com.barberexperience.application.usecases.cliente;

import com.barberexperience.application.gattewars.cliente.BuscarClientePorIdUseCase;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarClientePorIdUseCaseImpl implements BuscarClientePorIdUseCase {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public Optional<ClienteDomain> execute(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        return clienteRepository.findById(id);
    }
} 