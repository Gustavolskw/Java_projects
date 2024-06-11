package med.voll.api.domain.consulta;

import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultas(Long id, Long idMedico, Long idPaciente, LocalDateTime data, Especialidade especialidade) {
    public DadosDetalhamentoConsultas(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMedico().getEspecialidade());
    }
}
