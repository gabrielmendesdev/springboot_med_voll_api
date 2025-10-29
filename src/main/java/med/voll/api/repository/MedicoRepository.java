package med.voll.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.entity.Medico;
import med.voll.api.enums.medico.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                SELECT m FROM Medico m
                WHERE m.ativo = true
                  AND m.especialidade = :especialidade
                  AND m.id NOT IN (
                      SELECT c.medico.id FROM Consulta c
                      WHERE c.data = :data
                  )
            """)
    List<Medico> listarMedicosDisponiveis(Especialidade especialidade, LocalDateTime data);

}
