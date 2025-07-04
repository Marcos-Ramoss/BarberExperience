package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.valueobjects.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarProfissionalRequest {
    private String nome;
    private List<Especialidade> especialidades;
} 