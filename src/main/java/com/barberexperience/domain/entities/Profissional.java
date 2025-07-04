package com.barberexperience.domain.entities;

import java.util.List;

import com.barberexperience.domain.valueobjects.Especialidade;
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
public class Profissional {
    private Long id;
    private String nome;
    private List<Especialidade> especialidades;
    // outros atributos relevantes
    // getters, setters, construtores
} 