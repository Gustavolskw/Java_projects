package med.voll.api.domain.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

   Page<Consulta> findAllByCanceladaFalse(Pageable pageable);

   boolean existsByMedicoIdAndDataAndMotivoDoCancelamentoIsNull(Long idMedico, LocalDateTime data);

   Boolean existsByPacienteIdAndDataBetween(Long IdPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
