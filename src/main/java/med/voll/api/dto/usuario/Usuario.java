package med.voll.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record Usuario(
    @NotBlank(message = "Login é obrigatório")
    String login,
    @NotBlank(message = "Senha é obrigatória")
    String senha
) {  
}
