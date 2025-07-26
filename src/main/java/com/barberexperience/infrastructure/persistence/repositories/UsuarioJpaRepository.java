package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.domain.repositories.UsuarioRepository;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioJpaRepository implements UsuarioRepository {
    private final UsuarioSpringDataRepository usuarioSpringDataRepository;

    public UsuarioJpaRepository(UsuarioSpringDataRepository usuarioSpringDataRepository) {
        this.usuarioSpringDataRepository = usuarioSpringDataRepository;
    }

    @Override
    public Optional<UsuarioEntity> findByUsername(String username) {
        return usuarioSpringDataRepository.findByUsername(username);
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioSpringDataRepository.save(usuario);
    }
}
