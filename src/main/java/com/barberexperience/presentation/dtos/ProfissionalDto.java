package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do profissional")
public record ProfissionalDto(

    @Schema(description = "ID do profissional", example = "1") 
    Long id,
    
    @Schema(description = "Nome do profissional", example = "Pedro Santos") 
    String nome
) {} 