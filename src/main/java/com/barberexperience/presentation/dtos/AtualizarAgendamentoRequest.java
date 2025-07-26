package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Request para atualizar um agendamento")
public record AtualizarAgendamentoRequest(
    @Schema(description = "Data e hora do agendamento (formato: yyyy-MM-ddTHH:mm:ss)", example = "2025-01-15T14:00:00")
    LocalDateTime dataHora,
    
    @Schema(description = "ID do cliente", example = "1")
    Long clienteId,
    
    @Schema(description = "ID do profissional", example = "1")
    Long profissionalId,
    
    @Schema(description = "ID do serviço", example = "1")
    Long servicoId,
    
    @Schema(description = "ID da barbearia", example = "1")
    Long barbeariaId,
    
    @Schema(description = "Status do agendamento", example = "CONFIRMADO")
    String status
) {} 