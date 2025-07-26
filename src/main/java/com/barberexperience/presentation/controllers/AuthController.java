package com.barberexperience.presentation.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barberexperience.infrastructure.persistence.entities.UsuarioEntity;
import com.barberexperience.infrastructure.persistence.repositories.UsuarioSpringDataRepository;
import com.barberexperience.presentation.dtos.AuthResponseDto;
import com.barberexperience.presentation.dtos.LoginRequestDto;
import com.barberexperience.presentation.dtos.RegisterRequestDto;
import com.barberexperience.presentation.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    private final UsuarioSpringDataRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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
                .build();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    @Operation(summary = "Fazer login e obter token JWT")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), usuarioOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
        UsuarioEntity usuario = usuarioOpt.get();
        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRole().name());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
