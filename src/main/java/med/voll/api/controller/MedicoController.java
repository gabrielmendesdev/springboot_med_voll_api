package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.AtualizarDadosMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.service.MedicoService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoMedico> save(@RequestBody @Valid DadosCadastroMedico medico, UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoMedico medicoDTO = medicoService.salvarMedico(medico);
        URI uri = uriBuilder.path("/medicos/" + medicoDTO.id()).build().toUri();
        return ResponseEntity.created(uri).body(medicoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> buscarDetalhesMedico(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarDetalhesById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemMedico> atualizarMedico(@PathVariable Long id, @RequestBody @Valid AtualizarDadosMedico dados) {
        return ResponseEntity.ok(medicoService.atualizarMedico(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }
     
    @GetMapping("/dados")
    public ResponseEntity<Page<DadosListagemMedico>> buscarDadosListagemMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(medicoService.buscarDadosListagemMedicos(paginacao));
    }

}
