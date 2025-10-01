package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.service.MedicoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;


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
    
}
