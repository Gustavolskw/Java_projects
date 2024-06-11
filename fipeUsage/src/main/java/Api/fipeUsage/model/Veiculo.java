package Api.fipeUsage.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") String anoModelo,
                      @JsonAlias("Combustivel") String combustivel,
                      @JsonAlias("CodigoFipe") String fipeCode,
                      @JsonAlias("MesReferencia")String mesReferencia,
                      @JsonAlias("SiglaCombustivel")String siglaCombustivel) {
    @Override
    public String toString() {
        return "\nVeiculo escolhido" +
                "\nValor = " + valor +
                "\nMarca = " + marca +
                "\nModelo = " + modelo +
                "\nAno do Modelo = " + anoModelo +
                "\nTipo de Combustivel = " + combustivel +
                "\nCodigo Fipe = " + fipeCode +
                "\nMês de Referencia = " + mesReferencia +
                "\nSigla do Combustivel = ("+siglaCombustivel+")";
    }
}
