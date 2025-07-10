package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do cliente")
public record ClienteDto(

    @Schema(description = "ID do cliente", example = "1") 
    Long id,

    @Schema(description = "Nome do cliente", example = "João Silva") 
    String nome,
    
    @Schema(description = "Email do cliente", example = "joao@email.com") 
    String email
) {} 