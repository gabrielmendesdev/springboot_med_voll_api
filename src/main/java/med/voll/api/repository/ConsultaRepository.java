package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.entity.Consulta;
import med.voll.api.entity.Medico;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoAndData(Medico medico, LocalDateTime data);
    boolean existsByMedicoAndDataBetween(Medico medico, LocalDateTime dataInicio, LocalDateTime dataFim);
}