package com.barberexperience.domain.repositories;

import java.util.Optional;

import com.barberexperience.domain.ServicoDomain;

import java.util.List;

public interface ServicoRepository {
    Optional<ServicoDomain> findById(Long id);
    List<ServicoDomain> findAll();
    ServicoDomain save(ServicoDomain servico);
    void deleteById(Long id);
} 