package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Horário de funcionamento")
public record HorarioFuncionamentoDto(

    @Schema(description = "Hora de abertura", example = "08:00") 
    String abertura,
    
    @Schema(description = "Hora de fechamento", example = "18:00") 
    String fechamento
) {} 