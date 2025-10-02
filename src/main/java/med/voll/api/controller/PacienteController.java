package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.AtualizarDadosMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.dto.paciente.AtualizarDadosPaciente;
import med.voll.api.dto.paciente.DadosCadastroPaciente;
import med.voll.api.dto.paciente.DadosListagemPaciente;
import med.voll.api.entity.Paciente;
import med.voll.api.service.PacienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente pacienteDto) {
        pacienteService.salvarPaciente(pacienteDto);
    }
    
    @GetMapping("dados")
    public Page<DadosListagemPaciente> buscarDadosListagemPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return pacienteService.buscarDadosListagemPacientes(paginacao);
    }

    @PutMapping("editar/{id}")
    public DadosListagemPaciente atualizarPaciente(@PathVariable Long id, @RequestBody @Valid AtualizarDadosPaciente dados) {
        return pacienteService.atualizarPaciente(id, dados);
    }

    @DeleteMapping("deletar/{id}")
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }
}
