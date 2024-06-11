package med.voll.api.domain.consulta;

import med.voll.api.domain.medicos.Especialidade;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.pacientes.Paciente;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, Long medicoId, Long  pacienteId, LocalDateTime data, Especialidade especialidade) {

    public DadosListagemConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMedico().getEspecialidade() );
    }
}
