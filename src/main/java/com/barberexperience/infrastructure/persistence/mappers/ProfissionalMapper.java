package com.barberexperience.infrastructure.persistence.mappers;

import java.util.List;
import org.springframework.stereotype.Component;
import com.barberexperience.domain.entities.Profissional;
import com.barberexperience.infrastructure.persistence.entities.ProfissionalEntity;

@Component
public class ProfissionalMapper {
    
    public Profissional toDomain(ProfissionalEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Profissional.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .especialidades(List.of(entity.getEspecialidade()))
                .build();
    }
    
    public ProfissionalEntity toEntity(Profissional profissional) {
        if (profissional == null) {
            return null;
        }
        
        ProfissionalEntity entity = new ProfissionalEntity();
        entity.setId(profissional.getId());
        entity.setNome(profissional.getNome());
        
        // Pega a primeira especialidade da lista (simplificação)
        if (profissional.getEspecialidades() != null && !profissional.getEspecialidades().isEmpty()) {
            entity.setEspecialidade(profissional.getEspecialidades().get(0));
        }
        
        return entity;
    }
} 