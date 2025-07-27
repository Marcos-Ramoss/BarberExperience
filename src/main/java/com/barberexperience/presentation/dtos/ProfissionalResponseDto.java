package com.barberexperience.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Resposta com dados do profissional")
public record ProfissionalResponseDto(
    @Schema(description = "ID do profissional", example = "1")
    Long id,
    
    @Schema(description = "Nome do profissional", example = "João Silva")
    String nome,
    
    @Schema(description = "CPF do profissional", example = "123.456.789-00")
    String cpf,
    
    @Schema(description = "Telefone do profissional", example = "(11) 99999-9999")
    String telefone,
    
    @Schema(description = "Email do profissional", example = "joao@email.com")
    String email,
    
    @Schema(description = "Especialidades do profissional", example = "[\"CORTE_MASCULINO\", \"BARBA\"]")
    List<String> especialidades,
    
    @Schema(description = "ID da barbearia", example = "1")
    Long barbeariaId
) {} 