package com.barberexperience.domain.repositories;

import com.barberexperience.domain.entities.Cliente;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository {
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    Cliente save(Cliente cliente);
    void deleteById(Long id);
} 