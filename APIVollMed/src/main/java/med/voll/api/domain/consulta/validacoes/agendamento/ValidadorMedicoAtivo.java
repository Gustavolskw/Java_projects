package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidarAgendamentoDeConsulta{
    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamento dados){
        if(dados.idMedico()==null){
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Medico não esta ativo");
        }
    }
}
