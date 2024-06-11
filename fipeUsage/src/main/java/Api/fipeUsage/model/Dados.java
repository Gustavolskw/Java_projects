package Api.fipeUsage.model;


public record Dados(Integer codigo, String nome) {
    @Override
    public String toString() {
        return "Codigo = " + codigo +
                " || Marca = " + nome+"\n";
    }
}
