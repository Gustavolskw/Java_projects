package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.OptionalDouble;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serie {
    private  String titulo;
    private  Integer totalTemporadas;
    private  Double avaliacao;
    private Categoria generos;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.avaliacao())).orElse(0.0);
        this.generos = Categoria.fromPortugues(dadosSerie.generos().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();

    }

    @Override
    public String toString() {
        return "\n--Serie--" +
                "\ntitulo= " + titulo +
                "\ntotalTemporadas= " + totalTemporadas +
                "\navaliacao= " + avaliacao +
                "\ngeneros= " + generos +
                "\natores= " + atores +
                "\nposter= " + poster +
                "\nsinopse= " + sinopse;
    }
}
