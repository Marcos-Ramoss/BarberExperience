package com.barberexperience.presentation.dtos;

import java.math.BigDecimal;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para atualizar uma barbearia")
public record AtualizarBarbeariaRequest(
    @Schema(description = "Nome da barbearia", example = "BarbaOn")
    String nome,
    
    @Schema(description = "CNPJ da barbearia", example = "84.555.777/4200-01")
    String cnpj,
    
    @Schema(description = "Telefone da barbearia", example = "(92) 98411-2010")
    String telefone,
    
    @Schema(description = "Email da barbearia", example = "barbaOn@gmail.com")
    String email,
    
    @Schema(description = "Rua do endereço", example = "Monte Granaro")
    String rua,
    
    @Schema(description = "Número do endereço", example = "100")
    String numero,
    
    @Schema(description = "Bairro do endereço", example = "Chapada")
    String bairro,
    
    @Schema(description = "Cidade do endereço", example = "Manaus")
    String cidade,
    
    @Schema(description = "Estado do endereço", example = "AM")
    String estado,
    
    @Schema(description = "CEP do endereço", example = "69050-008")
    String cep,
    
    @Schema(description = "Horário de abertura (formato HH:mm:ss)", example = "08:00:00", type = "string")
    LocalTime abertura,
    
    @Schema(description = "Horário de fechamento (formato HH:mm:ss)", example = "18:00:00", type = "string")
    LocalTime fechamento
) {} 