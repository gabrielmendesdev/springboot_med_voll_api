package med.voll.api.dto.agendamentos;

import med.voll.api.enums.medico.MotivoCancelamento;

public record DadosCancelamentoConsulta(Long idConsulta, MotivoCancelamento motivoCancelamento) {
    
}
