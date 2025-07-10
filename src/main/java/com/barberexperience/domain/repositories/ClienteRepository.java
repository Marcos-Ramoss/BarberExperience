package com.barberexperience.domain.repositories;

import java.util.Optional;

import com.barberexperience.domain.ClienteDomain;

import java.util.List;

public interface ClienteRepository {
    Optional<ClienteDomain> findById(Long id);
    List<ClienteDomain> findAll();
    ClienteDomain save(ClienteDomain cliente);
    void deleteById(Long id);
} 