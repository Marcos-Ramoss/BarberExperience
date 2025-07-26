package com.barberexperience.presentation.dtos;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para atualizar um serviço")
public record AtualizarServicoRequest(
    @Schema(description = "Nome do serviço", example = "Corte Masculino")
    String nome,
    
    @Schema(description = "Descrição do serviço", example = "Corte tradicional masculino")
    String descricao,
    
    @Schema(description = "Preço do serviço", example = "25.00")
    BigDecimal preco,
    
    @Schema(description = "Duração em minutos", example = "30")
    Integer duracaoMinutos,
    
    @Schema(description = "ID da barbearia", example = "1")
    Long barbeariaId
) {} 