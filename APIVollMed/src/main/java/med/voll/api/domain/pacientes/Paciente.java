package med.voll.api.domain.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Endereco;

@Getter
@Table(name = "pacientes")
@Entity(name = "Paciente")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(DadosCadastroPacientes dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.ativo= true;
    }



    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if(dados.nome()!=null){
            this.nome = dados.nome();
        }
        if(dados.telefone() !=null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() !=null){
            this.endereco.atualizaDados(dados.endereco());
        }
    }
    public void excluiPaciente() {
        this.ativo=false;
    }

}
