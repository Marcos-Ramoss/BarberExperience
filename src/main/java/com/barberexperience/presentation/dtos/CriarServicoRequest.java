package com.barberexperience.presentation.dtos;

import java.math.BigDecimal;

public record CriarServicoRequest(
    String nome,
    String descricao,
    BigDecimal preco,
    Integer duracaoMinutos,
    Long barbeariaId
) {} 