package com.barberexperience.domain;

import java.util.List;
import java.util.ArrayList;
import com.barberexperience.domain.valueobjects.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfissionalDomain {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private BarbeariaDomain barbearia;
    private UsuarioDomain usuario;

    @Default
    private List<Especialidade> especialidades = new ArrayList<>();

    // ====== Regras de Negócio ======

    /**
     * Atualiza o nome do profissional.
     */
    public void atualizarNome(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    /**
     * Atualiza o CPF do profissional.
     */
    public void atualizarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        this.cpf = cpf;
    }

    /**
     * Atualiza o telefone do profissional.
     */
    public void atualizarTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Atualiza o email do profissional.
     */
    public void atualizarEmail(String email) {
        this.email = email;
    }

    /**
     * Associa o profissional a uma barbearia.
     */
    public void associarBarbearia(BarbeariaDomain barbearia) {
        if (barbearia == null) {
            throw new IllegalArgumentException("Barbearia não pode ser nula.");
        }
        this.barbearia = barbearia;
    }
    
    /**
     * Associa o profissional a um usuário.
     */
    public void associarUsuario(UsuarioDomain usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        this.usuario = usuario;
    }

    /**
     * Adiciona uma especialidade ao profissional.
     */
    public void adicionarEspecialidade(Especialidade especialidade) {
        if (!this.especialidades.contains(especialidade)) {
            this.especialidades.add(especialidade);
        }
    }

    /**
     * Remove uma especialidade do profissional.
     */
    public void removerEspecialidade(Especialidade especialidade) {
        this.especialidades.remove(especialidade);
    }

    /**
     * Verifica se o profissional possui uma especialidade.
     */
    public boolean possuiEspecialidade(Especialidade especialidade) {
        return this.especialidades.contains(especialidade);
    }
}