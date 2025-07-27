package com.barberexperience.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDomain {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    public enum Role {
        ADMIN,
        BARBEARIA,
        PROFISSIONAL,
        CLIENTE
    }
} 