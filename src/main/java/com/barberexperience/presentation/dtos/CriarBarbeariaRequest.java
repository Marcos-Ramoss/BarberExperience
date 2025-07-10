package com.barberexperience.presentation.dtos;

public record CriarBarbeariaRequest(
    String nome,
    String cnpj,
    String telefone,
    String email,
    String rua,
    String numero,
    String bairro,
    String cidade,
    String estado,
    String cep,
    String horaAbertura,
    String horaFechamento
) { }