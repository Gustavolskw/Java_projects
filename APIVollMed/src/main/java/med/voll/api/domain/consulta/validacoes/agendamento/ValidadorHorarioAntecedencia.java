package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidarAgendamentoDeConsulta{

    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var difEmMinutos = Duration.between(agora,dataConsulta).toMinutes();
        if(difEmMinutos<30){
            throw new ValidacaoException("Consulta deve ser agendada com no minimo Antecedencia de 30 Minutos");
        }
    }

}
