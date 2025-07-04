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
} 