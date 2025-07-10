package com.barberexperience.application.usecases.agendamento;

import com.barberexperience.application.gattewars.agendamento.CriarAgendamentoUseCase;
import com.barberexperience.domain.AgendamentoDomain;
import com.barberexperience.domain.ClienteDomain;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.AgendamentoRepository;
import com.barberexperience.domain.repositories.ClienteRepository;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.domain.valueobjects.StatusAgendamento;
import com.barberexperience.presentation.dtos.CriarAgendamentoRequest;

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
    public AgendamentoDomain execute(CriarAgendamentoRequest request) {
        // Validações de negócio
        validarDadosAgendamento(request);
        
        // Buscar entidades relacionadas
        ClienteDomain cliente = buscarCliente(request.clienteId());
        ProfissionalDomain profissional = buscarProfissional(request.profissionalId());
        List<ServicoDomain> servicos = buscarServicos(request.servicoIds());
        
        // Validações de disponibilidade
        validarDisponibilidade(profissional, request.horario(), servicos);
        
        // Criação da entidade de domínio
        AgendamentoDomain agendamento = AgendamentoDomain.builder()
                .cliente(cliente)
                .profissional(profissional)
                .servicos(servicos)
                .status(StatusAgendamento.PENDENTE)
                .build();
        
        // Delegar a validação de horário e status para o domínio
        agendamento.agendar(request.horario());
        
        // Persistência via interface (não depende de implementação)
        return agendamentoRepository.save(agendamento);
    }
    
    private void validarDadosAgendamento(CriarAgendamentoRequest request) {
        if (request.clienteId() == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        
        if (request.profissionalId() == null) {
            throw new IllegalArgumentException("ID do profissional é obrigatório");
        }
        
        if (request.servicoIds() == null || request.servicoIds().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos um serviço é obrigatório");
        }
        
        if (request.horario() == null) {
            throw new IllegalArgumentException("Horário é obrigatório");
        }
        // Remover validação de horário no passado, pois agora está no domínio
    }
    
    private ClienteDomain buscarCliente(Long clienteId) {
        Optional<ClienteDomain> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente.get();
    }
    
    private ProfissionalDomain buscarProfissional(Long profissionalId) {
        Optional<ProfissionalDomain> profissional = profissionalRepository.findById(profissionalId);
        if (profissional.isEmpty()) {
            throw new IllegalArgumentException("Profissional não encontrado");
        }
        return profissional.get();
    }
    
    private List<ServicoDomain> buscarServicos(List<Long> servicoIds) {
        List<ServicoDomain> servicos = servicoRepository.findAll().stream()
                .filter(servico -> servicoIds.contains(servico.getId()))
                .toList();
        
        if (servicos.size() != servicoIds.size()) {
            throw new IllegalArgumentException("Um ou mais serviços não foram encontrados");
        }
        
        return servicos;
    }
    
    private void validarDisponibilidade(ProfissionalDomain profissional, LocalDateTime horario, List<ServicoDomain> servicos) {
       
        if (horario.getHour() < 8 || horario.getHour() > 20) {
            throw new IllegalArgumentException("Horário fora do período de funcionamento");
        }
    }
} 