package AluraPlay.Api.Domain.Videos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVideosRepository extends JpaRepository<Videos, Long> {
    @Query("SELECT v FROM videos v WHERE v.titulo LIKE %:nome%")
    List<Videos> fiandAllPorNome(String nome);

}
