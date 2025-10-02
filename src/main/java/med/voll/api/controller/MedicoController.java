package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.AtualizarDadosMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.service.MedicoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Medico save(@RequestBody @Valid DadosCadastroMedico medico) {
        return medicoService.salvarMedico(medico);
    }
     
    @GetMapping("dados")
    public Page<DadosListagemMedico> buscarDadosListagemMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoService.buscarDadosListagemMedicos(paginacao);
    }

    @PutMapping("editar/{id}")
    public DadosListagemMedico atualizarMedico(@PathVariable Long id, @RequestBody @Valid AtualizarDadosMedico dados) {
        return medicoService.atualizarMedico(id, dados);
    }

    @DeleteMapping("deletar/{id}")
    public void deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
    }
}
