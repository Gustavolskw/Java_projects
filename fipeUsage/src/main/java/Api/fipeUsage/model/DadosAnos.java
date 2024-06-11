package Api.fipeUsage.model;

public record DadosAnos(String codigo, String nome) {
    @Override
    public String toString() {
        return "Codigo = " + codigo +
                " || Marca = " + nome+"\n";
    }
}

