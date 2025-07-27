package com.barberexperience.domain.repositories;

import com.barberexperience.domain.UsuarioDomain;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    UsuarioDomain save(UsuarioDomain usuario);
    Optional<UsuarioDomain> findById(Long id);
    Optional<UsuarioDomain> findByUsername(String username);
    Optional<UsuarioDomain> findByUsernameAndAtivoTrue(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<UsuarioDomain> findAll();
    void deleteById(Long id);
}
