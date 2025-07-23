package com.barberexperience.application.usecases.cliente;

import com.barberexperience.application.gattewars.cliente.ListarClientesUseCase;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarClientesUseCaseImpl implements ListarClientesUseCase {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public List<ClienteDomain> execute() {
        return clienteRepository.findAll();
    }
} 