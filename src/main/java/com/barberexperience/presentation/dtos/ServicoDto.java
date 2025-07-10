package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do serviço")
public record ServicoDto(

    @Schema(description = "ID do serviço", example = "1") 
    Long id,

    @Schema(description = "Nome do serviço", example = "Corte Masculino") 
    String nome,

    @Schema(description = "Preço do serviço", example = "25.00") 
    String preco,
    
    @Schema(description = "Duração em minutos", example = "30") 
    Integer duracaoMinutos
) {} 