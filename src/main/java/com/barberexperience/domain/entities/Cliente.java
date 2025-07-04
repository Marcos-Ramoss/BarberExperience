package com.barberexperience.domain.entities;

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
public class Cliente {
    private Long id;
    private String nome;
    private String email;
    // outros atributos relevantes
    // getters, setters, construtores
} 