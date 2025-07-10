package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Resposta de erro da API")
public record ErrorResponse(
    
    @Schema(description = "Timestamp do erro", example = "2024-01-15T14:30:00")
    LocalDateTime timestamp,

    @Schema(description = "Código de status HTTP", example = "400")
    Integer status,

    @Schema(description = "Tipo do erro", example = "Bad Request")
    String error,

    @Schema(description = "Mensagem de erro", example = "Nome da barbearia é obrigatório")
    String message,

    @Schema(description = "Caminho da requisição", example = "/api/v1/barbearias")
    String path
) {}