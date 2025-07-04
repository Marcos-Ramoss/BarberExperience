package com.barberexperience.domain.repositories;

import com.barberexperience.domain.entities.Servico;
import java.util.Optional;
import java.util.List;

public interface ServicoRepository {
    Optional<Servico> findById(Long id);
    List<Servico> findAll();
    Servico save(Servico servico);
    void deleteById(Long id);
} 