package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DadosCancelamentoConsulta (@NotNull
                                         @JsonAlias({"id", "consulta", "idConsulta"})
                                         Long idConsulta,
                                         @NotNull
                                         @JsonAlias({"motivo", "cancelamento"})
                                         MotivoDoCancelamento motivoDoCancelamento
) {
}
