package com.barberexperience.domain.entities;

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
public class Servico {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer duracaoMinutos;
    // getters, setters, construtores
} 