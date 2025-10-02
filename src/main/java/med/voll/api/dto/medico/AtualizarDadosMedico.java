package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.entity.Endereco;

public record AtualizarDadosMedico(
    @NotBlank
    String nome,
    @NotBlank
    String telefone,
    @Valid
    Endereco endereco) {
}