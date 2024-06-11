package AluraPlay.Api.Domain.Videos;

import jakarta.validation.constraints.NotBlank;

public record PostVideos(@NotBlank String titulo,
                         @NotBlank
                                  String descricao,
                         @NotBlank
                                  String url,
                         @NotBlank
                                  String imagem) {
}
