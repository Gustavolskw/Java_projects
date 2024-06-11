package Model;

import java.util.ArrayList;

public class Cep {
    String cep;
    String rua;
    String cidade;
    String bairro;



    public Cep(CepViaCep cepViaCep) {
        this.cep = cepViaCep.cep();
        this.rua = cepViaCep.logradouro();
        this.bairro = cepViaCep.bairro();
        this.cidade = cepViaCep.localidade();
    }




    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Cep: "+ cep+
                "\nRua: "+rua+
                "\nCidade: "+cidade+
                "\nBairro: "+ bairro;
    }
}
