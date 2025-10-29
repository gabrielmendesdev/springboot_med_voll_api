package med.voll.api.dto.agendamentos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.entity.Consulta;
import med.voll.api.enums.medico.Especialidade;

public record DadosDetalhamentoConsulta(
        @NotNull
        Long id,

        @NotNull
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
                public DadosDetalhamentoConsulta(Consulta consulta) {
                    this(
                        consulta.getId(),
                        consulta.getMedico().getId(),
                        consulta.getPaciente().getId(),
                        consulta.getData(),
                        consulta.getMedico().getEspecialidade()
                    );
                }
}
