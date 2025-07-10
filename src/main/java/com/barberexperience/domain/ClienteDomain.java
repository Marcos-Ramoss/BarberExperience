package com.barberexperience.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDomain {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDateTime dataNascimento;

    // ====== Regras de Negócio ======

    /**
     * Atualiza o nome do cliente.
     */
    public void atualizarNome(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    /**
     * Atualiza o CPF do cliente.
     */
    public void atualizarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        this.cpf = cpf;
    }

    /**
     * Atualiza o telefone do cliente.
     */
    public void atualizarTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Atualiza a data de nascimento do cliente.
     */
    public void atualizarDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Atualiza o e-mail do cliente, validando o formato.
     */
    public void atualizarEmail(String novoEmail) {
        if (!validarEmail(novoEmail)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = novoEmail;
    }

    /**
     * Valida o formato do e-mail.
     */
    public boolean validarEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
} 