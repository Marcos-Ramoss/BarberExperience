package com.barberexperience.application.usecases.barbearia;

import com.barberexperience.application.gattewars.barbearia.AtualizarBarbeariaUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import com.barberexperience.presentation.dtos.AtualizarBarbeariaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarBarbeariaUseCaseImpl implements AtualizarBarbeariaUseCase {
    
    private final BarbeariaRepository barbeariaRepository;
    
    @Override
    public BarbeariaDomain execute(Long id, AtualizarBarbeariaRequest request) {
        validarDadosBarbearia(request);
        
        BarbeariaDomain barbearia = barbeariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada com ID: " + id));
        
        // Atualizar campos básicos
        if (request.nome() != null) {
            barbearia.atualizarNome(request.nome());
        }
        
        if (request.cnpj() != null) {
            barbearia.atualizarCnpj(request.cnpj());
        }
        
        if (request.telefone() != null) {
            barbearia.atualizarTelefone(request.telefone());
        }
        
        if (request.email() != null) {
            barbearia.atualizarEmail(request.email());
        }
        
        // Atualizar endereço se fornecido
        if (request.rua() != null || request.numero() != null ||
            request.bairro() != null || request.cidade() != null || request.estado() != null || request.cep() != null) {
            
            Endereco novoEndereco = new Endereco(
                request.rua() != null ? request.rua() : barbearia.getEndereco().getRua(),
                request.numero() != null ? request.numero() : barbearia.getEndereco().getNumero(),
                request.bairro() != null ? request.bairro() : barbearia.getEndereco().getBairro(),
                request.cidade() != null ? request.cidade() : barbearia.getEndereco().getCidade(),
                request.estado() != null ? request.estado() : barbearia.getEndereco().getEstado(),
                request.cep() != null ? request.cep() : barbearia.getEndereco().getCep()
            );
            barbearia.atualizarEndereco(novoEndereco);
        }
        
        // Atualizar horário de funcionamento se fornecido
        if (request.abertura() != null || request.fechamento() != null) {
            HorarioFuncionamento novoHorario = new HorarioFuncionamento(
                request.abertura() != null ? request.abertura() : barbearia.getHorarioFuncionamento().getAbertura(),
                request.fechamento() != null ? request.fechamento() : barbearia.getHorarioFuncionamento().getFechamento()
            );
            barbearia.atualizarHorarioFuncionamento(novoHorario);
        }
        
        return barbeariaRepository.save(barbearia);
    }
    
    private void validarDadosBarbearia(AtualizarBarbeariaRequest request) {
        if (request.cnpj() != null && request.cnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode estar vazio");
        }
        
        if (request.nome() != null && request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }
        
        if (request.email() != null && !request.email().contains("@")) {
            throw new IllegalArgumentException("Email deve conter @");
        }
        
        if (request.abertura() != null && request.fechamento() != null) {
            if (request.abertura().isAfter(request.fechamento())) {
                throw new IllegalArgumentException("Horário de abertura deve ser anterior ao fechamento");
            }
        }
    }
} 