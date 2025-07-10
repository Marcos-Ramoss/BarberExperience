package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de dados da barbearia")
public record BarbeariaResponse(
    
    @Schema(description = "ID da barbearia", example = "1")
    Long id,

    @Schema(description = "Nome da barbearia", example = "Barbearia do João")
    String nome,

    @Schema(description = "CNPJ da barbearia", example = "12.345.678/0001-90")
    String cnpj,

    @Schema(description = "Telefone da barbearia", example = "(11) 99999-9999")
    String telefone,

    @Schema(description = "Email da barbearia", example = "contato@barbearia.com")
    String email,

    @Schema(description = "Endereço da barbearia")
    EnderecoDto endereco,

    @Schema(description = "Horário de funcionamento")
    HorarioFuncionamentoDto horarioFuncionamento
) {}