package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.valueobjects.Especialidade;
import java.util.List;

public record CriarProfissionalRequest(
    String nome,
    String cpf,
    String telefone,
    String email,
    List<Especialidade> especialidades,
    Long barbeariaId
) { }