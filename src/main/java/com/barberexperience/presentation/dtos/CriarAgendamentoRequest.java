package com.barberexperience.presentation.dtos;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para criar um novo agendamento")
public record CriarAgendamentoRequest(
    @Schema(description = "ID do cliente", example = "1")
    Long clienteId,
    @Schema(description = "ID do profissional", example = "1")
    Long profissionalId,
    @Schema(description = "ID da barbearia", example = "1")
    Long barbeariaId,
    @Schema(description = "IDs dos serviços", example = "[1, 2]")
    List<Long> servicoIds,
    @Schema(description = "Data e hora do agendamento (formato: yyyy-MM-ddTHH:mm:ss)", example = "2025-01-15T14:00:00")
    LocalDateTime horario
) { }