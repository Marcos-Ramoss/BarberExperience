package com.barberexperience.infrastructure.persistence.mappers;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.infrastructure.persistence.entities.ProfissionalEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProfissionalMapper {

    private final BarbeariaMapper barbeariaMapper;
    private final UsuarioMapper usuarioMapper;

    public ProfissionalDomain toDomain(ProfissionalEntity entity) {
        if (entity == null) {
            return null;
        }

        ProfissionalDomain profissional = ProfissionalDomain.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .especialidades(entity.getEspecialidades() != null ? new ArrayList<>(entity.getEspecialidades()) : new ArrayList<>())
                .build();
        
        if (entity.getBarbearia() != null) {
            profissional.associarBarbearia(barbeariaMapper.toDomain(entity.getBarbearia()));
        }
        
        // ✅ MAPEAMENTO DO USUÁRIO
        if (entity.getUsuario() != null) {
            profissional.associarUsuario(usuarioMapper.toDomain(entity.getUsuario()));
        }

        return profissional;
    }

    public ProfissionalEntity toEntity(ProfissionalDomain profissional) {
        if (profissional == null) {
            return null;
        }

        ProfissionalEntity entity = new ProfissionalEntity();
        entity.setId(profissional.getId());
        entity.setNome(profissional.getNome());
        entity.setCpf(profissional.getCpf());
        entity.setTelefone(profissional.getTelefone());
        entity.setEmail(profissional.getEmail());
        entity.setEspecialidades(profissional.getEspecialidades() != null ? new ArrayList<>(profissional.getEspecialidades()) : new ArrayList<>());
        
        if (profissional.getBarbearia() != null) {
            entity.setBarbearia(barbeariaMapper.toEntity(profissional.getBarbearia()));
        }
        
        // ✅ MAPEAMENTO DO USUÁRIO
        if (profissional.getUsuario() != null) {
            entity.setUsuario(usuarioMapper.toEntity(profissional.getUsuario()));
        }

        return entity;
    }
}