package AluraPlay.Api.Controller;

import AluraPlay.Api.Domain.Videos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("videos")
public class VideosController {

    @Autowired
    private IVideosRepository videosRepository;

    @PostMapping
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity cadastrarVideos(@RequestBody @Valid PostVideos postVideos, UriComponentsBuilder uriComponentsBuilder){
        var video = new Videos(postVideos);
        videosRepository.save(video);
        var uri = uriComponentsBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosVideo(video));
    }
    @GetMapping
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity listar (){
        var videos = videosRepository.findAll().stream().map(GetVideos::new);
        return ResponseEntity.ok(videos);
    }
    @GetMapping("/")
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity listar2 (){
        var videos = videosRepository.findAll().stream().map(GetVideos::new);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{titulo}")
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity detalhar(@PathVariable String titulo){
        var videos = videosRepository.fiandAllPorNome(titulo).stream().map(GetVideos::new);

        return ResponseEntity.ok().body(videos);
    }





}
