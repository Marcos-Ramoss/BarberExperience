package com.barberexperience.presentation.dtos;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de dados do agendamento")
public record AgendamentoResponse(
    
    @Schema(description = "ID do agendamento", example = "1")
    Long id,

    @Schema(description = "Dados do cliente")
    ClienteDto cliente,

    @Schema(description = "Dados do profissional")
    ProfissionalDto profissional,

    @Schema(description = "Lista de serviços")
    List<ServicoDto> servicos,

    @Schema(description = "Horário do agendamento", example = "2024-01-15T14:30:00")
    LocalDateTime horario,

    @Schema(description = "Status do agendamento", example = "PENDENTE")
    String status
) {}