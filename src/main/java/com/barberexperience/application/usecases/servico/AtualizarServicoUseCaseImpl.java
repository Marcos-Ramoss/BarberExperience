package com.barberexperience.application.usecases.servico;

import com.barberexperience.application.gattewars.servico.AtualizarServicoUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.presentation.dtos.AtualizarServicoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AtualizarServicoUseCaseImpl implements AtualizarServicoUseCase {
    
    private final ServicoRepository servicoRepository;
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public ServicoDomain execute(Long id, AtualizarServicoRequest request) {
        validarDadosServico(request);
        
        ServicoDomain servico = servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com ID: " + id));
        
        // Atualizar dados do serviço usando as regras de negócio do domínio
        if (request.nome() != null) {
            servico.atualizarNome(request.nome());
        }
        
        if (request.descricao() != null) {
            servico.atualizarDescricao(request.descricao());
        }
        
        if (request.preco() != null) {
            servico.atualizarPreco(request.preco());
        }
        
        if (request.duracaoMinutos() != null) {
            servico.atualizarDuracao(request.duracaoMinutos());
        }
        
        // Atualizar barbearia se fornecida
        if (request.barbeariaId() != null) {
            BarbeariaDomain barbearia = barbeariaRepository.findById(request.barbeariaId())
                    .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada com ID: " + request.barbeariaId()));
            servico.associarBarbearia(barbearia);
        }
        
        return servicoRepository.save(servico);
    }
    
    private void validarDadosServico(AtualizarServicoRequest request) {
        if (request.preco() != null && request.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        
        if (request.duracaoMinutos() != null && request.duracaoMinutos() <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
    }
} 