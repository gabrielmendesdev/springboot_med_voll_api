package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.dto.paciente.AtualizarDadosPaciente;
import med.voll.api.dto.paciente.DadosCadastroPaciente;
import med.voll.api.dto.paciente.DadosDetalhamentoPaciente;
import med.voll.api.dto.paciente.DadosListagemPaciente;
import med.voll.api.service.PacienteService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody DadosCadastroPaciente pacienteDto, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoPaciente paciente = pacienteService.salvarPaciente(pacienteDto);
        URI uri = uriBuilder.path("/pacientes/" + paciente.id()).build().toUri();
        return ResponseEntity.created(uri).body(paciente);
    }
    
    @GetMapping("dados")
    public ResponseEntity<Page<DadosListagemPaciente>> buscarDadosListagemPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(pacienteService.buscarDadosListagemPacientes(paginacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemPaciente> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid AtualizarDadosPaciente dados) {
        DadosListagemPaciente paciente = pacienteService.atualizarPaciente(id, dados);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> buscarDetalhesPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarDetalhesById(id));
    }
}
