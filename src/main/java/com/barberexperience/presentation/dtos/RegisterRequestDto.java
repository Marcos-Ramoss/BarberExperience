package com.barberexperience.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    private String username;
    private String password;
    private String email;
    private UsuarioEntity.Role role;
    // Adicione outros campos importantes para registro conforme necessário
}
