package AluraPlay.Api.Domain.Videos;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "videos")
@Entity(name = "videos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private String imagem;

    public Videos(PostVideos dados){
        this.titulo = dados.titulo();
        this.descricao= dados.descricao();
        this.url = dados.url();
        this.imagem = dados.imagem();
    }

}
