package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.domain.pacientes.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadoPacienteAtivo implements ValidarAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamento dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta noa pode ser agendada com paciente excluido");
        }
    }
}
