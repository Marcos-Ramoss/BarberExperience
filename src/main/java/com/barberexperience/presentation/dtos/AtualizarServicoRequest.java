package com.barberexperience.presentation.dtos;

import java.math.BigDecimal;

public record AtualizarServicoRequest(
    String nome,
    String descricao,
    BigDecimal preco,
    Integer duracaoMinutos,
    Long barbeariaId
) {} 