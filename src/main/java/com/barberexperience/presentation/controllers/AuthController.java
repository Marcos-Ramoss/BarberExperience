package com.barberexperience.presentation.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import com.barberexperience.infrastructure.persistence.repositories.UsuarioSpringDataRepository;
import com.barberexperience.presentation.dtos.*;
import com.barberexperience.presentation.security.JwtUtil;
import com.barberexperience.domain.repositories.ProfissionalRepository;
import com.barberexperience.domain.ProfissionalDomain;
import com.barberexperience.infrastructure.persistence.mappers.ProfissionalResponseDtoMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    private final UsuarioSpringDataRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalResponseDtoMapper profissionalResponseDtoMapper;

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username já existe");
        }
        UsuarioEntity usuario = UsuarioEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .ativo(true)
                .build();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    @Operation(summary = "Fazer login e obter token JWT")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            
            // ✅ BUSCAR DADOS DO USUÁRIO PARA OBTER A ROLE CORRETA
            var usuarioOpt = usuarioRepository.findByUsername(request.username());
            String userRole = "USER"; // Role padrão
            
            if (usuarioOpt.isPresent()) {
                userRole = usuarioOpt.get().getRole().name();
            }
            
            String token = jwtUtil.generateToken(authentication.getName(), userRole);
            
            // ✅ BUSCAR DADOS DO PROFISSIONAL (apenas se for PROFISSIONAL)
            ProfissionalResponseDto profissional = null;
            if ("PROFISSIONAL".equals(userRole)) {
                try {
                    Optional<ProfissionalDomain> profissionalOpt = profissionalRepository.findByUsuarioUsername(request.username());
                    if (profissionalOpt.isPresent()) {
                        profissional = profissionalResponseDtoMapper.toDto(profissionalOpt.get());
                    }
                } catch (Exception e) {
                    // Usuário não é profissional
                }
            }
            
            return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .user(UserDto.builder()
                    .id(authentication.getName())
                    .username(authentication.getName())
                    .role(userRole)
                    .profissional(profissional)
                    .build())
                .build());
                
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
    
    @GetMapping("/usuarios/username/{username}")
    @Operation(summary = "Buscar usuário por username")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> buscarUsuarioPorUsername(@PathVariable String username) {
        try {
            var usuarioOpt = usuarioRepository.findByUsername(username);
            if (usuarioOpt.isPresent()) {
                var usuario = usuarioOpt.get();
                return ResponseEntity.ok(UserDto.builder()
                    .id(usuario.getUsername())
                    .username(usuario.getUsername())
                    .role(usuario.getRole().name())
                    .profissional(null) // Não incluir dados do profissional neste endpoint
                    .build());
            } else {
                return ResponseEntity.status(404).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao buscar usuário");
        }
    }
    
    @GetMapping("/profissionais/username/{username}")
    @Operation(summary = "Buscar profissional por username (email/CPF)")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> buscarProfissionalPorUsername(@PathVariable String username) {
        try {
            // ✅ PRIMEIRO VERIFICAR SE O USUÁRIO EXISTE E É UM PROFISSIONAL
            var usuarioOpt = usuarioRepository.findByUsername(username);
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Usuário não encontrado");
            }
            
            var usuario = usuarioOpt.get();
            if (!"PROFISSIONAL".equals(usuario.getRole().name())) {
                return ResponseEntity.status(403).body("Usuário não é um profissional");
            }
            
            // ✅ AGORA BUSCAR OS DADOS DO PROFISSIONAL
            Optional<ProfissionalDomain> profissionalOpt = profissionalRepository.findByUsuarioUsername(username);
            if (profissionalOpt.isPresent()) {
                ProfissionalResponseDto profissional = profissionalResponseDtoMapper.toDto(profissionalOpt.get());
                return ResponseEntity.ok(profissional);
            } else {
                return ResponseEntity.status(404).body("Dados do profissional não encontrados");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao buscar profissional");
        }
    }
}
