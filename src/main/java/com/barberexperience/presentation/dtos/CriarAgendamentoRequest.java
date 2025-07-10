package com.barberexperience.presentation.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record CriarAgendamentoRequest(
    Long clienteId,
    Long profissionalId,
    List<Long> servicoIds,
    LocalDateTime horario
) { }