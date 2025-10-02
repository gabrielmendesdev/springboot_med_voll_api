package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.entity.Endereco;

public record AtualizarDadosPaciente(
    @NotBlank
    String nome,
    @NotBlank
    String telefone,
    @Valid
    Endereco endereco) {
}