package com.barberexperience.application.usecases.profissional;

import com.barberexperience.application.gattewars.profissional.CriarProfissionalUseCase;
import com.barberexperience.domain.BarbeariaDomain;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.domain.UsuarioDomain;
import com.barberexperience.domain.repositories.BarbeariaRepository;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProfissionalUseCaseImpl implements CriarProfissionalUseCase {
    
    private final ProfissionalRepository profissionalRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public ProfissionalDomain execute(CriarProfissionalRequest request) {
        // Validações de negócio
        validarDadosProfissional(request);
        
        // ✅ VALIDAR SE EMAIL/CPF JÁ EXISTE
        if (usuarioRepository.existsByUsername(request.email())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        
        if (usuarioRepository.existsByUsername(request.cpf())) {
            throw new IllegalArgumentException("CPF já está em uso");
        }
        
        // ✅ VALIDAR SE SENHAS COINCIDEM
        if (!request.isSenhasIguais()) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }
        
        // ✅ CRIAR USUÁRIO
        UsuarioDomain usuario = UsuarioDomain.builder()
                .username(request.email()) // Usar email como username
                .email(request.email())
                .password(passwordEncoder.encode(request.senha()))
                .role(UsuarioDomain.Role.PROFISSIONAL)
                .ativo(true)
                .build();
        
        usuario = usuarioRepository.save(usuario);
        
        // Buscar a barbearia
        BarbeariaDomain barbearia = barbeariaRepository.findById(request.barbeariaId())
            .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada com ID: " + request.barbeariaId()));
        
        // Criação da entidade de domínio
        ProfissionalDomain profissional = ProfissionalDomain.builder().build();
        profissional.atualizarNome(request.nome());
        profissional.atualizarCpf(request.cpf());
        profissional.atualizarTelefone(request.telefone());
        profissional.atualizarEmail(request.email());
        profissional.associarBarbearia(barbearia);
        profissional.associarUsuario(usuario);
        request.especialidades().forEach(profissional::adicionarEspecialidade);
        
        // Persistência via interface (não depende de implementação)
        return profissionalRepository.save(profissional);
    }
    
    private void validarDadosProfissional(CriarProfissionalRequest request) {
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }
        
        if (request.cpf() == null || request.cpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do profissional é obrigatório");
        }
        
        if (request.email() == null || request.email().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do profissional é obrigatório");
        }
        
        if (request.senha() == null || request.senha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha do profissional é obrigatória");
        }
        
        if (request.senha().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 6 caracteres");
        }
        
        if (request.confirmarSenha() == null || request.confirmarSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Confirmação de senha é obrigatória");
        }
        
        if (request.especialidades() == null || request.especialidades().isEmpty()) {
            throw new IllegalArgumentException("Pelo menos uma especialidade é obrigatória");
        }
        
        if (request.barbeariaId() == null) {
            throw new IllegalArgumentException("ID da barbearia é obrigatório");
        }
    }
} 