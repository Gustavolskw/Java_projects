package med.voll.api.domain.pacientes;

import med.voll.api.domain.Endereco;

public record DadosDetalhadosPaciente(Long id, String nome, String email, String telefone, Endereco endereco) {
    public DadosDetalhadosPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco() );
    }
}
