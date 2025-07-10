package com.barberexperience.domain.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioFuncionamento {
    private LocalTime abertura;
    private LocalTime fechamento;

    /**
     * Verifica se o horário informado está dentro do horário de funcionamento.
     */
    public boolean isAbertoEm(java.time.LocalDateTime horario) {
        if (abertura == null || fechamento == null) return false;
        java.time.LocalTime hora = horario.toLocalTime();
        return !hora.isBefore(abertura) && !hora.isAfter(fechamento);
    }
} 