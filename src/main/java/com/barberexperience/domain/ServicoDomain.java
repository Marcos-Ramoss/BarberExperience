package com.barberexperience.domain;

import java.math.BigDecimal;
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
public class ServicoDomain {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer duracaoMinutos;
    private BarbeariaDomain barbearia;

    // ====== Regras de Negócio ======

    /**
     * Atualiza o nome do serviço.
     */
    public void atualizarNome(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    /**
     * Atualiza a descrição do serviço.
     */
    public void atualizarDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Associa o serviço a uma barbearia.
     */
    public void associarBarbearia(BarbeariaDomain barbearia) {
        if (barbearia == null) {
            throw new IllegalArgumentException("Barbearia não pode ser nula.");
        }
        this.barbearia = barbearia;
    }

    /**
     * Atualiza o preço do serviço, validando valor positivo.
     */
    public void atualizarPreco(BigDecimal novoPreco) {
        if (novoPreco == null || novoPreco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        this.preco = novoPreco;
    }

    /**
     * Atualiza a duração do serviço, validando valor positivo.
     */
    public void atualizarDuracao(Integer novaDuracao) {
        if (novaDuracao == null || novaDuracao <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero.");
        }
        this.duracaoMinutos = novaDuracao;
    }
} 