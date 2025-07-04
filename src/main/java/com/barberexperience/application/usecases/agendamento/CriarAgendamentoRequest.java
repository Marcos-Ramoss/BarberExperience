package com.barberexperience.application.usecases.agendamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarAgendamentoRequest {
    private Long clienteId;
    private Long profissionalId;
    private List<Long> servicoIds;
    private LocalDateTime horario;
} 