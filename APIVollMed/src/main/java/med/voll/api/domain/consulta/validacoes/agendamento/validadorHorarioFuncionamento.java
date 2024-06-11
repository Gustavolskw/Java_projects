package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class validadorHorarioFuncionamento implements ValidarAgendamentoDeConsulta{
    public void validar(DadosAgendamento dadosAgendamento){
        var dataConsulta = dadosAgendamento.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var anteAberturaDaClinica = dataConsulta.getHour()<7;
        var depoisDoEncerramentoClinica = dataConsulta.getHour()>18;
        if(domingo||anteAberturaDaClinica||depoisDoEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
        }
    }
}
