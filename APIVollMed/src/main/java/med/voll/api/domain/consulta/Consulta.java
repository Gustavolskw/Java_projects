package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.pacientes.Paciente;

import java.time.LocalDateTime;
@Getter
@Table(name = "consultas")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;
    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoDoCancelamento motivoDoCancelamento;
    private Boolean cancelada ;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data) {
        this.id=id;
        this.medico=medico;
        this.paciente=paciente;
        this.data=data;
    }


    public void cancelar(MotivoDoCancelamento motivo) {
        this.motivoDoCancelamento = motivo;
        this.cancelada=true;
    }
}
