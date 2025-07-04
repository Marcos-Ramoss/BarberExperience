package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.domain.entities.Agendamento;
import com.barberexperience.domain.entities.Cliente;
import com.barberexperience.domain.entities.Profissional;
import com.barberexperience.domain.entities.Servico;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.domain.valueobjects.StatusAgendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {
    
    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    
    @Override
    public Agendamento execute(CriarAgendamentoRequest request) {
        // Validações de negócio
        validarDadosAgendamento(request);
        
        // Buscar entidades relacionadas
        Cliente cliente = buscarCliente(request.getClienteId());
        Profissional profissional = buscarProfissional(request.getProfissionalId());
        List<Servico> servicos = buscarServicos(request.getServicoIds());
        
        // Validações de disponibilidade
        validarDisponibilidade(profissional, request.getHorario(), servicos);
        
        // Criação da entidade de domínio
        Agendamento agendamento = Agendamento.builder()
                .cliente(cliente)
                .profissional(profissional)
                .servicos(servicos)
                .horario(request.getHorario())
                .status(StatusAgendamento.PENDENTE)
                .build();
        
        // Persistência via interface (não depende de implementação)
        return agendamentoRepository.save(agendamento);
    }
    
    private void validarDadosAgendamento(CriarAgendamentoRequest request) {
        if (request.getClienteId() == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        
        if (request.getProfissionalId() == null) {
            throw new IllegalArgumentException("ID do profissional é obrigatório");
        }
        
        if (request.getServicoIds() == null || request.getServicoIds().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos um serviço é obrigatório");
        }
        
        if (request.getHorario() == null) {
            throw new IllegalArgumentException("Horário é obrigatório");
        }
        
        if (request.getHorario().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Horário não pode ser no passado");
        }
    }
    
    private Cliente buscarCliente(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente.get();
    }
    
    private Profissional buscarProfissional(Long profissionalId) {
        Optional<Profissional> profissional = profissionalRepository.findById(profissionalId);
        if (profissional.isEmpty()) {
            throw new IllegalArgumentException("Profissional não encontrado");
        }
        return profissional.get();
    }
    
    private List<Servico> buscarServicos(List<Long> servicoIds) {
        List<Servico> servicos = servicoRepository.findAll().stream()
                .filter(servico -> servicoIds.contains(servico.getId()))
                .toList();
        
        if (servicos.size() != servicoIds.size()) {
            throw new IllegalArgumentException("Um ou mais serviços não foram encontrados");
        }
        
        return servicos;
    }
    
    private void validarDisponibilidade(Profissional profissional, LocalDateTime horario, List<Servico> servicos) {
        // TODO: Implementar validação de disponibilidade do profissional
        // Por enquanto, apenas uma validação básica
        if (horario.getHour() < 8 || horario.getHour() > 20) {
            throw new IllegalArgumentException("Horário fora do período de funcionamento");
        }
    }
} 