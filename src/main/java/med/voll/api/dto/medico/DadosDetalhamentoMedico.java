package med.voll.api.dto.medico;

import med.voll.api.entity.Endereco;
import med.voll.api.entity.Medico;
import med.voll.api.enums.medico.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
