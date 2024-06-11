package AluraPlay.Api.Domain.Videos;

import java.util.ArrayList;

public record GetVideos(String titulo, String descricao, String url, String imagem) {

    public GetVideos(Videos video){
        this(video.getTitulo(), video.getDescricao(), video.getUrl(), video.getImagem());
    }
}
