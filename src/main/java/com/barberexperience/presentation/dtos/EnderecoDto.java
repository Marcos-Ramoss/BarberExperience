package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Endereço")
public record EnderecoDto(
    @Schema(description = "Rua", example = "Rua das Flores") 
    String rua,

    @Schema(description = "Número", example = "123") 
    String numero,

    @Schema(description = "Bairro", example = "Centro") 
    String bairro,

    @Schema(description = "Cidade", example = "São Paulo") 
    String cidade,

    @Schema(description = "Estado", example = "SP") 
    String estado,
    
    @Schema(description = "CEP", example = "01234-567") 
    String cep
) {} 