package com.barberexperience.domain.entities;

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
public class Barbearia {
    private Long id;
    private NomeBarbearia nome;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private List<Profissional> profissionais;
    // getters, setters, construtores
} 