package com.barberexperience.infrastructure.persistence.mappers;

import com.barberexperience.domain.UsuarioDomain;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    public UsuarioDomain toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return UsuarioDomain.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(UsuarioDomain.Role.valueOf(entity.getRole().name()))
                .ativo(entity.getAtivo())
                .dataCriacao(entity.getDataCriacao())
                .dataAtualizacao(entity.getDataAtualizacao())
                .build();
    }
    
    public UsuarioEntity toEntity(UsuarioDomain usuario) {
        if (usuario == null) {
            return null;
        }
        
        return UsuarioEntity.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .role(UsuarioEntity.Role.valueOf(usuario.getRole().name()))
                .ativo(usuario.getAtivo())
                .dataCriacao(usuario.getDataCriacao())
                .dataAtualizacao(usuario.getDataAtualizacao())
                .build();
    }
} 