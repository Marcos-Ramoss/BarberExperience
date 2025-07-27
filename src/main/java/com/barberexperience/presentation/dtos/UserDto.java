package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados do usuário")
public class UserDto {
    @Schema(description = "ID do usuário (username)", example = "joao@email.com")
    private String id;
    
    @Schema(description = "Username do usuário", example = "joao@email.com")
    private String username;
    
    @Schema(description = "Role do usuário", example = "PROFISSIONAL")
    private String role;
    
    @Schema(description = "Dados do profissional (se aplicável)")
    private ProfissionalResponseDto profissional;
} 