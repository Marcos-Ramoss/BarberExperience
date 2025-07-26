package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para atualizar um cliente")
public record AtualizarClienteRequest(
    @Schema(description = "Nome do cliente", example = "João Silva")
    String nome,
    
    @Schema(description = "CPF do cliente", example = "123.456.789-00")
    String cpf,
    
    @Schema(description = "Telefone do cliente", example = "(92) 98411-2010")
    String telefone,
    
    @Schema(description = "Data de nascimento (formato dd/MM/yyyy)", example = "15/03/1990")
    String dataNascimento
) {} 