package com.barberexperience.domain.repositories;

import java.util.Optional;

import com.barberexperience.domain.ProfissionalDomain;

import java.util.List;

public interface ProfissionalRepository {
    Optional<ProfissionalDomain> findById(Long id);
    List<ProfissionalDomain> findAll();
    ProfissionalDomain save(ProfissionalDomain profissional);
    void deleteById(Long id);
    
    Optional<ProfissionalDomain> findByUsuarioUsername(String username);
} 