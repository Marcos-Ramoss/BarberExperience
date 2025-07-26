package com.barberexperience.domain;

import java.util.List;

import com.barberexperience.domain.valueobjects.Endereco;
import com.barberexperience.domain.valueobjects.HorarioFuncionamento;
import com.barberexperience.domain.valueobjects.NomeBarbearia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarbeariaDomain {
    private Long id;
    private NomeBarbearia nome;
    private String cnpj;
    private String telefone;
    private String email;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private List<ProfissionalDomain> profissionais;
   

    /**
     * Atualiza o nome da barbearia.
     */
    public void atualizarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = new NomeBarbearia(nome);
    }

    /**
     * Atualiza o CNPJ da barbearia.
     */
    public void atualizarCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser vazio.");
        }
        this.cnpj = cnpj;
    }

    /**
     * Atualiza o telefone da barbearia.
     */
    public void atualizarTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Atualiza o email da barbearia.
     */
    public void atualizarEmail(String email) {
        this.email = email;
    }

    /**
     * Adiciona um profissional à barbearia.
     */
    public void adicionarProfissional(ProfissionalDomain profissional) {
        this.profissionais.add(profissional);
    }

    /**
     * Remove um profissional da barbearia.
     */
    public void removerProfissional(ProfissionalDomain profissional) {
        this.profissionais.remove(profissional);
    }

    /**
     * Atualiza o endereço da barbearia.
     */
    public void atualizarEndereco(Endereco novoEndereco) {
        this.endereco = novoEndereco;
    }

    /**
     * Atualiza o horário de funcionamento da barbearia.
     */
    public void atualizarHorarioFuncionamento(HorarioFuncionamento novoHorario) {
        this.horarioFuncionamento = novoHorario;
    }

    /**
     * Verifica se a barbearia está aberta em um determinado horário.
     * (Exemplo de regra, depende da implementação de HorarioFuncionamento)
     */
    public boolean estaAbertaEm(java.time.LocalDateTime horario) {
        // Supondo que HorarioFuncionamento tenha um método isAbertoEm(LocalDateTime)
        return this.horarioFuncionamento != null && this.horarioFuncionamento.isAbertoEm(horario);
    }

    /**
     * Busca um profissional pelo id.
     */
    public ProfissionalDomain buscarProfissionalPorId(Long profissionalId) {
        return this.profissionais.stream()
            .filter(p -> p.getId().equals(profissionalId))
            .findFirst()
            .orElse(null);
    }
} 