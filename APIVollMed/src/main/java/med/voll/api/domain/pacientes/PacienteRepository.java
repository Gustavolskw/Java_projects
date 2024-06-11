package med.voll.api.domain.pacientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
@Query("""
        SELECT p.ativo
        FROM  Paciente p
        WHERE
        p.id = :id
        """)
    Boolean findAtivoById(Long id);
}
