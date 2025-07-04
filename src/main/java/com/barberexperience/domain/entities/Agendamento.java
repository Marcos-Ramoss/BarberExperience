package com.barberexperience.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import com.barberexperience.domain.valueobjects.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private List<Servico> servicos;
    private LocalDateTime horario;
    private StatusAgendamento status;
    // getters, setters, construtores
} 