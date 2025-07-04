package com.barberexperience.domain.valueobjects;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HorarioFuncionamento {
    private final LocalTime abertura;
    private final LocalTime fechamento;
} 