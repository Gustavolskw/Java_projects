package AluraPlay.Api.Domain.Videos;

public record DadosVideo(Long id, String titulo, String descricao, String url, String imagem) {
    public DadosVideo(Videos videos){
        this(videos.getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl(), videos.getImagem());
    }
}
