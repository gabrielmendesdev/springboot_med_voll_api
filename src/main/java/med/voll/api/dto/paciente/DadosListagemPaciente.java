package med.voll.api.dto.paciente;  

import med.voll.api.entity.Paciente;

public record DadosListagemPaciente(String nome, String email, String cpf, String telefone) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }
}