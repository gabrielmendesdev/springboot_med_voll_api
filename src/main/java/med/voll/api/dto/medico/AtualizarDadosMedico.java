package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.entity.Endereco;

public record AtualizarDadosMedico(
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    @NotBlank(message = "Telefone é obrigatório")
    String telefone,
    @Valid
    Endereco endereco) {
}