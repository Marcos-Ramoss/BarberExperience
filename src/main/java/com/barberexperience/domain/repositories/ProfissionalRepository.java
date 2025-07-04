package com.barberexperience.domain.repositories;

import com.barberexperience.domain.entities.Profissional;
import java.util.Optional;
import java.util.List;

public interface ProfissionalRepository {
    Optional<Profissional> findById(Long id);
    List<Profissional> findAll();
    Profissional save(Profissional profissional);
    void deleteById(Long id);
} 