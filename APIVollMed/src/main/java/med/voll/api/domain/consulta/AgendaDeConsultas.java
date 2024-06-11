package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.agendamento.ValidarAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidarMedicoComConsultaMesmoHorario;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidarCancelamentoDeConsulta;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidarAgendamentoDeConsulta> validadores;
    @Autowired
    private List<ValidarCancelamentoDeConsulta> validadoresCancelamento;






    public DadosDetalhamentoConsultas agendar(DadosAgendamento dadosAgendamento){
        if(!pacienteRepository.existsById(dadosAgendamento.idPaciente())){
            throw new ValidacaoException("Id de paciente não Encontrado");
        }
        if (dadosAgendamento.idMedico()!=null && !medicoRepository.existsById(dadosAgendamento.idMedico())){
            throw new ValidacaoException("Id de Médico não Encontrado");
        }

        validadores.forEach(v->v.validar(dadosAgendamento));

        var paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());
        var medico = escolherMedico(dadosAgendamento);
        if(medico ==null){
            throw new ValidacaoException("Naoexiste medico disponivel nessa data");
        }
        var consulta = new Consulta(null, medico, paciente, dadosAgendamento.data(),null, false);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsultas(consulta);
    }

    private Medico escolherMedico(DadosAgendamento dadosAgendamento) {
        if (dadosAgendamento.idMedico()!=null){
            return medicoRepository.getReferenceById(dadosAgendamento.idMedico());
        }
        if(dadosAgendamento.especialidade() == null){
            throw new ValidacaoException("Especialidade é Obrigatoria caso medico nao especificado");
        }
        return medicoRepository.escolherMedicoAleatorioLivre(dadosAgendamento.especialidade(),dadosAgendamento.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoDoCancelamento());
    }
}
