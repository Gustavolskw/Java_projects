package Vulkan.api.voitures.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class Episodio {
    private int temporada;
    private String episodio;
    private double avaliacao;
    private int episodioNum;
    private LocalDate dataLancamento;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DecimalFormat df =new DecimalFormat("##.##");

    public Episodio(Integer temporada, DadosEpisodio e) {
        this.temporada = temporada;
        this.episodio = e.titulo();
        this.episodioNum = e.episodio();
        try {
            this.avaliacao = Double.parseDouble(e.avaliacao());
        }catch (Exception expet){
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(e.dataLancamento());
        }catch (Exception exept){
            this.dataLancamento = null;
        }

    }

    @Override
    public String toString() {
        return "\nEpisodio :" +
                "\nTemporada = " + temporada +
                "\nNome do Episodio = " + episodio +
                "\nNumero do Episodio = "+episodioNum+
                "\nAvaliacao = " + df.format(avaliacao)+
                "\nData de Lancamento = " + dataLancamento.format(formatter);
    }
}
