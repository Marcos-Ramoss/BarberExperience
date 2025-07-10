package com.barberexperience.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.barberexperience.domain.valueobjects.StatusAgendamento;
import com.barberexperience.presentation.exceptions.AgendamentoInvalidoException;
import com.barberexperience.presentation.exceptions.HorarioInvalidoException;
import com.barberexperience.presentation.exceptions.StatusAgendamentoInvalidoException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoDomain {
    private Long id;
    private ClienteDomain cliente;
    private ProfissionalDomain profissional;
    private BarbeariaDomain barbearia;
    private LocalDateTime dataHora;
    private StatusAgendamento status;
    private String observacoes;

    @Default
    private List<ServicoDomain> servicos = new ArrayList<>();

    // ====== Regras de Negócio ======

    /**
     * Agenda um novo horário para o agendamento.
     * Pode lançar exceção se o status não permitir.
     */
    public void agendar(LocalDateTime novoHorario) {
        if (this.status != StatusAgendamento.PENDENTE) {
            throw new StatusAgendamentoInvalidoException("Só é possível agendar se o status for PENDENTE.");
        }
        validarHorario(novoHorario);
        this.dataHora = novoHorario;
        this.status = StatusAgendamento.CONFIRMADO;
    }

    /**
     * Cancela o agendamento.
     */
    public void cancelar() {
        if (this.status == StatusAgendamento.CANCELADO) {
            throw new StatusAgendamentoInvalidoException("Agendamento já está cancelado.");
        }
        this.status = StatusAgendamento.CANCELADO;
    }

    /**
     * Reagenda para um novo horário.
     */
    public void reagendar(LocalDateTime novoHorario) {
        if (this.status != StatusAgendamento.CONFIRMADO) {
            throw new StatusAgendamentoInvalidoException("Só é possível reagendar se o status for CONFIRMADO.");
        }
        validarHorario(novoHorario);
        this.dataHora = novoHorario;
    }

    /**
     * Finaliza o agendamento.
     */
    public void finalizar() {
        if (this.status != StatusAgendamento.CONFIRMADO) {
            throw new StatusAgendamentoInvalidoException("Só é possível finalizar se o status for CONFIRMADO.");
        }
        this.status = StatusAgendamento.FINALIZADO;
    }

    /**
     * Adiciona um serviço ao agendamento.
     */
    public void adicionarServico(ServicoDomain servico) {
        this.servicos.add(servico);
    }

    /**
     * Remove um serviço do agendamento.
     */
    public void removerServico(ServicoDomain servico) {
        this.servicos.remove(servico);
    }

    /**
     * Atualiza as observações do agendamento.
     */
    public void atualizarObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * Valida se o horário é permitido (exemplo: não pode ser no passado).
     */
    public void validarHorario(LocalDateTime horario) {
        if (horario.isBefore(LocalDateTime.now())) {
            throw new HorarioInvalidoException("Horário não pode ser no passado.");
        }
        // Outras validações podem ser adicionadas aqui
    }

    /**
     * Altera o status do agendamento, se permitido.
     */
    public void alterarStatus(StatusAgendamento novoStatus) {
        // Exemplo de regra: não pode voltar para PENDENTE
        if (novoStatus == StatusAgendamento.PENDENTE) {
            throw new StatusAgendamentoInvalidoException("Não é permitido voltar para o status PENDENTE.");
        }
        this.status = novoStatus;
    }
} 