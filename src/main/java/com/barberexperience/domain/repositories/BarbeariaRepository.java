package com.barberexperience.domain.repositories;

import java.util.Optional;

import com.barberexperience.domain.BarbeariaDomain;

import java.util.List;

public interface BarbeariaRepository {
    Optional<BarbeariaDomain> findById(Long id);
    List<BarbeariaDomain> findAll();
    BarbeariaDomain save(BarbeariaDomain barbearia);
    void deleteById(Long id);
} 