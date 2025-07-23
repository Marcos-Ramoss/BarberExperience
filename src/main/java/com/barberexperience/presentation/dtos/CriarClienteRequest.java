package com.barberexperience.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CriarClienteRequest(
    String nome,
    String cpf,
    String telefone,
    String email,
    @JsonFormat(pattern = "dd/MM/yyyy")
    String dataNascimento
) {} 