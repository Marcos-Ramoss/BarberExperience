package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.application.gattewars.agendamento.AtualizarAgendamentoUseCase;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.domain.valueobjects.StatusAgendamento;
import com.barberexperience.presentation.dtos.AtualizarAgendamentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarAgendamentoUseCaseImpl implements AtualizarAgendamentoUseCase {
    
    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public AgendamentoDomain execute(Long id, AtualizarAgendamentoRequest request) {
        validarDadosAgendamento(request);
        
        AgendamentoDomain agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com ID: " + id));
        
        // Atualizar data e hora se fornecida
        if (request.dataHora() != null) {
            agendamento.reagendar(request.dataHora());
        }
        
        // Atualizar cliente se fornecido
        if (request.clienteId() != null) {
            ClienteDomain cliente = clienteRepository.findById(request.clienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + request.clienteId()));
            agendamento.alterarCliente(cliente);
        }
        
        // Atualizar profissional se fornecido
        if (request.profissionalId() != null) {
            ProfissionalDomain profissional = profissionalRepository.findById(request.profissionalId())
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com ID: " + request.profissionalId()));
            agendamento.alterarProfissional(profissional);
        }
        
        // Atualizar serviço se fornecido
        if (request.servicoId() != null) {
            ServicoDomain servico = servicoRepository.findById(request.servicoId())
                    .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com ID: " + request.servicoId()));
            agendamento.alterarServico(servico);
        }
        
        // Atualizar barbearia se fornecida
        if (request.barbeariaId() != null) {
            BarbeariaDomain barbearia = barbeariaRepository.findById(request.barbeariaId())
                    .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada com ID: " + request.barbeariaId()));
            agendamento.alterarBarbearia(barbearia);
        }
        
        // Atualizar status se fornecido
        if (request.status() != null) {
            StatusAgendamento novoStatus = StatusAgendamento.valueOf(request.status().toUpperCase());
            agendamento.alterarStatus(novoStatus);
        }
        
        return agendamentoRepository.save(agendamento);
    }
    
    private void validarDadosAgendamento(AtualizarAgendamentoRequest request) {
        if (request.dataHora() != null && request.dataHora().isBefore(java.time.LocalDateTime.now())) {
            throw new IllegalArgumentException("Data e hora do agendamento não pode ser no passado");
        }
        
        if (request.status() != null) {
            try {
                StatusAgendamento.valueOf(request.status().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status inválido. Status válidos: PENDENTE, CONFIRMADO, CANCELADO, FINALIZADO");
            }
        }
    }
} 