package com.barberexperience.domain.repositories;

import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<UsuarioEntity> findByUsername(String username);
    UsuarioEntity save(UsuarioEntity usuario);
}
