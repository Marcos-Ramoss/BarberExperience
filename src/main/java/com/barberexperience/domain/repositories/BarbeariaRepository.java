package com.barberexperience.domain.repositories;

import com.barberexperience.domain.entities.Barbearia;
import java.util.Optional;
import java.util.List;

public interface BarbeariaRepository {
    Optional<Barbearia> findById(Long id);
    List<Barbearia> findAll();
    Barbearia save(Barbearia barbearia);
    void deleteById(Long id);
} 