package com.barberexperience.application.usecases.servico;

import com.barberexperience.application.gattewars.servico.CriarServicoUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ServicoDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.repositories.ServicoRepository;
import com.barberexperience.presentation.dtos.CriarServicoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CriarServicoUseCaseImpl implements CriarServicoUseCase {
    
    private final ServicoRepository servicoRepository;
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public ServicoDomain execute(CriarServicoRequest request) {
        validarDadosServico(request);
        
        BarbeariaDomain barbearia = barbeariaRepository.findById(request.barbeariaId())
                .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada"));
        
        ServicoDomain servico = ServicoDomain.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .duracaoMinutos(request.duracaoMinutos())
                .barbearia(barbearia)
                .build();
        
        return servicoRepository.save(servico);
    }
    
    private void validarDadosServico(CriarServicoRequest request) {
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        }
        
        if (request.preco() == null || request.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        
        if (request.duracaoMinutos() == null || request.duracaoMinutos() <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        
        if (request.barbeariaId() == null) {
            throw new IllegalArgumentException("ID da barbearia é obrigatório");
        }
    }
} 