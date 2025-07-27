package com.barberexperience.application.usecases.profissional;

import com.barberexperience.domain.valueobjects.Especialidade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "Request para criar um novo profissional com autenticação")
public record CriarProfissionalRequest(
    @Schema(description = "Nome completo do profissional", example = "João Silva")
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
    String nome,
    
    @Schema(description = "CPF no formato 000.000.000-00", example = "123.456.789-00")
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    String cpf,
    
    @Schema(description = "Telefone do profissional", example = "(11) 99999-9999")
    @NotBlank(message = "Telefone é obrigatório")
    String telefone,
    
    @Schema(description = "Email do profissional (será usado como username)", example = "joao@email.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    String email,
    
    @Schema(description = "Lista de especialidades do profissional", example = "[\"CORTE_MASCULINO\", \"BARBA\"]")
    @NotEmpty(message = "Especialidades são obrigatórias")
    List<Especialidade> especialidades,
    
    @Schema(description = "ID da barbearia onde o profissional trabalha", example = "1")
    @NotNull(message = "ID da barbearia é obrigatório")
    Long barbeariaId,
    
    @Schema(description = "Senha do profissional (mínimo 6 caracteres)", example = "123456")
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    String senha,
    
    @Schema(description = "Confirmação da senha", example = "123456")
    @NotBlank(message = "Confirmação de senha é obrigatória")
    String confirmarSenha
) {
    // Validador customizado para verificar se as senhas coincidem
    public boolean isSenhasIguais() {
        return senha != null && senha.equals(confirmarSenha);
    }
}