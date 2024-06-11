package modelo;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    private Long id;
    @Embedded
    private DadosPessoais dadosPessoais;


    public Cliente() {
    }

    public Cliente( String nome,  String cpf, Long id) {
        this.id = id;
        this.dadosPessoais = new DadosPessoais(nome, cpf );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }
    public String getNome(){
        return this.dadosPessoais.getNome();
    }
    public String getCpf(){
        return this.dadosPessoais.getCpf();
    }

}
