package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.medico.AtualizarDadosMedico;
import med.voll.api.dto.medico.DadosCadastroMedico;
import med.voll.api.dto.medico.DadosDetalhamentoMedico;
import med.voll.api.dto.medico.DadosListagemMedico;
import med.voll.api.entity.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public DadosDetalhamentoMedico salvarMedico(DadosCadastroMedico medicoDTO) {
        return new DadosDetalhamentoMedico(medicoRepository.save(new Medico(medicoDTO)));
    }

    public List<DadosDetalhamentoMedico> buscarMedicos() {
        return medicoRepository.findAll().stream()
                .map(DadosDetalhamentoMedico::new)
                .toList();
    }

    public Page<DadosListagemMedico> buscarDadosListagemMedicos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @Transactional
    public DadosListagemMedico atualizarMedico(Long id, AtualizarDadosMedico dados) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.atualizarInformacoes(dados);
        return new DadosListagemMedico(medico);
    }

    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }

    public DadosDetalhamentoMedico buscarDetalhesById(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return new DadosDetalhamentoMedico(medico);
    }
}
