package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.paciente.DadosCadastroPaciente;
import med.voll.api.dto.paciente.DadosListagemPaciente;
import med.voll.api.entity.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    @Transactional
    public Paciente salvarPaciente(DadosCadastroPaciente pacienteDTO) {
        Paciente entity = new Paciente(pacienteDTO);
        Paciente paciente = pacienteRepository.save(entity);
        return paciente;
    }

    public List<Paciente> buscarPacientes() {
        return pacienteRepository.findAll();
    }

    public Page<DadosListagemPaciente> buscarDadosListagemPacientes(Pageable paginacao) {
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
