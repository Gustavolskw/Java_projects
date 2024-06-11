package br.com.alura.screenmatch.model;

public enum Categoria  {

    AVENTURA("Adventure"),
    DRAMA("Drama"),
    FANTASIA("Fantasy"),
    COMEDIA("Comedy"),
    ROMANCE("Romance"),
    ACAO("Action"),
    FAMILIA("Family"),
    CRIME("Crime"),
    MISTERIO("Mystery");
    private String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }
    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
