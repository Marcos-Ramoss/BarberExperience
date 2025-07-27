package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request para login de usuário")
public record LoginRequest(
    @Schema(description = "Email ou CPF do usuário", example = "joao@email.com")
    @NotBlank(message = "Username é obrigatório")
    String username,
    
    @Schema(description = "Senha do usuário", example = "123456")
    @NotBlank(message = "Password é obrigatório")
    String password
) {} 