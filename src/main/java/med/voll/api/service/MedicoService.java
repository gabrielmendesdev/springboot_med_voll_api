package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico salvarMedico(DadosCadastroMedico medicoDTO) {
        Medico entity = new Medico(medicoDTO);
        Medico medico = medicoRepository.save(entity);
        return medico;
    }

    public List<Medico> buscarMedicos() {
        return medicoRepository.findAll();
    }

    public Page<DadosListagemMedico> buscarDadosListagemMedicos(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
    }
}
