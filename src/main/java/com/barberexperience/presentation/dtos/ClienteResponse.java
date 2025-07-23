package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Resposta de dados do cliente")
public record ClienteResponse(
    
    @Schema(description = "ID do cliente", example = "1")
    Long id,

    @Schema(description = "Nome do cliente", example = "João Silva")
    String nome,

    @Schema(description = "CPF do cliente", example = "123.456.789-00")
    String cpf,

    @Schema(description = "Telefone do cliente", example = "(11) 99999-9999")
    String telefone,

    @Schema(description = "Email do cliente", example = "joao@email.com")
    String email,

    @Schema(description = "Data de nascimento do cliente", example = "1990-01-01T00:00:00")
    LocalDateTime dataNascimento
) {} 