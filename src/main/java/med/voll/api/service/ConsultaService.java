package med.voll.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.agendamentos.DadosAgendamentoConsulta;
import med.voll.api.dto.agendamentos.DadosCancelamentoConsulta;
import med.voll.api.dto.agendamentos.DadosDetalhamentoConsulta;
import med.voll.api.entity.Consulta;
import med.voll.api.entity.Medico;
import med.voll.api.entity.Paciente;
import med.voll.api.errors.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dadosConsulta) {

        if (!pacienteRepository.existsById(dadosConsulta.idPaciente())) {
            throw new ValidacaoException("ID do paciente informado não existe!");
        }

        Medico medico;

        if (dadosConsulta.idMedico() == null) {
            if (dadosConsulta.especialidade() == null) {
                throw new ValidacaoException("Especialidade é obrigatória quando o médico não é informado!");
            }

            List<Medico> medicosDisponiveis = medicoRepository.listarMedicosDisponiveis(
                    dadosConsulta.especialidade(), dadosConsulta.data());

            if (medicosDisponiveis.isEmpty()) {
                throw new ValidacaoException("Não existe médico disponível nessa data!");
            }

            medico = medicosDisponiveis.get(ThreadLocalRandom.current().nextInt(medicosDisponiveis.size()));

        } else {
            if (!medicoRepository.existsById(dadosConsulta.idMedico())) {
                throw new ValidacaoException("ID do médico informado não existe!");
            }
            medico = medicoRepository.getReferenceById(dadosConsulta.idMedico());
        }

        LocalDateTime inicioIntervalo = dadosConsulta.data().minusHours(1);
        LocalDateTime fimIntervalo = dadosConsulta.data().plusHours(1);

        boolean jaAgendado = consultaRepository.existsByMedicoAndDataBetween(medico, inicioIntervalo, fimIntervalo);
        if (jaAgendado) {
            throw new ValidacaoException("O médico já possui consulta próxima a esse horário!");
        }

        Paciente paciente = pacienteRepository.getReferenceById(dadosConsulta.idPaciente());
        Consulta consulta = new Consulta(null, medico, paciente, dadosConsulta.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelarConsulta(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
