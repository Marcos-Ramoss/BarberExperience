package com.barberexperience.infrastructure.persistence.repositories;

import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSpringDataRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
    Optional<UsuarioEntity> findByUsernameAndAtivoTrue(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
