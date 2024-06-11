package Vulkan.api.voitures.control;

import Vulkan.api.voitures.model.*;
import Vulkan.api.voitures.scanner.ScannerInOut;
import Vulkan.api.voitures.service.ConsumoApi;
import Vulkan.api.voitures.service.ConvertData;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvertData convertData = new ConvertData();
    private final String ENDERECO= "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=d42fcab8";
    private DecimalFormat df =new DecimalFormat("#.##");

    private Scanner leitura = new Scanner(System.in).useDelimiter("\n");
    public void escolha() {
        int escolha;
        do {
            escolha = ScannerInOut.escolhaDeBusca();
            switch (escolha) {
                case 1:
                    movieOption();
                    break;
                case 2:
                    serieApiSearch();
                    break;
                case 3:
                    episodeApiSearch();
                    break;
                case 4:
                    seasonApiSearch();
                    break;
                case 5:
                    testSearch();
                    break;
                case 6:
                    mediaTemporada();
                    break;

            }

        }while(escolha != 7);
    }

    public void movieOption(){
        String filme = ScannerInOut.movieName();
        var json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+APIKEY);
        DadosFilmes datafilme =  convertData.getData(json, DadosFilmes.class);
        System.out.println(datafilme);
        ScannerInOut.proximoPasso();
    }

    public void serieApiSearch(){
        String filme = ScannerInOut.serieName();
        var json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+APIKEY);
        DataSerie dataSerie =  convertData.getData(json, DataSerie.class);
        System.out.println(dataSerie);
        ScannerInOut.proximoPasso();
    }
    public void episodeApiSearch(){
        String pesquisa = ScannerInOut.serieNameSeasonEpisode();
        System.out.println(pesquisa);
        var json = consumoApi.obterDados(ENDERECO+pesquisa+APIKEY);
        DadosEpisodio dataEpisodio =  convertData.getData(json, DadosEpisodio.class);
        System.out.println(dataEpisodio);
        ScannerInOut.proximoPasso();
    }

    public void seasonApiSearch(){
        String filme = ScannerInOut.serieName();
        var json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+APIKEY);
        DataSerie dataSerie =  convertData.getData(json, DataSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dataSerie.totalTemporadas();i++){
            json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+"&season=" + i + APIKEY);
            DadosTemporada dadosTemporada =  convertData.getData(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);


//        for(int i = 0; i < dataSerie.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j< episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

//        AtomicInteger i = new AtomicInteger(1);
//        temporadas.forEach(temp -> temp.episodios().forEach(episode ->System.out.println(i.getAndIncrement()+"-"+episode.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream())
                .collect(Collectors.toList());
        dadosEpisodios.forEach(System.out::println);

        System.out.println("##############################################\nTop 10 episodes\n######################################################");
        dadosEpisodios.stream()
                .filter(a->!a.avaliacao()
                        .equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e-> e.titulo().toUpperCase())
                .forEach(System.out::println);


        List<Episodio> episodios = temporadas.stream().flatMap(t->t.episodios().stream().map(e-> new Episodio(t.temporada(), e))).collect(Collectors.toList());

        episodios.stream().sorted(Comparator.comparing(Episodio::getAvaliacao).reversed()).limit(10).forEach(System.out::println);

        int ano = ScannerInOut.pesquisaAno();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Pesquisa por episodio acima de "+ano);
        LocalDate dataBusca = LocalDate.of(ano,1, 1);
        episodios.stream()
                .filter(e-> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .limit(10)
                .forEach(e-> System.out.println("\nTemporada: "+ e.getTemporada()+"\nEpisodio: "+e.getEpisodio()+"\nNumero do Episodio: "+e.getEpisodioNum()+"\nData de Lancamento: "+ e.getDataLancamento().format(formatter)));

        System.out.println("##############################################\nTop 10 episodes\n######################################################");

        dadosEpisodios.stream()
                .filter(a->!a.avaliacao()
                        .equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e-> e.titulo().toUpperCase())
                .forEach(System.out::println);

        ScannerInOut.proximoPasso();
    }

    public void testSearch(){
        String filme = ScannerInOut.serieName();
        var json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+APIKEY);
        DataSerie dataSerie =  convertData.getData(json, DataSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dataSerie.totalTemporadas();i++){
            json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+"&season=" + i + APIKEY);
            DadosTemporada dadosTemporada =  convertData.getData(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream())
                .collect(Collectors.toList());



//        dadosEpisodios.stream()
//                .filter(a->!a.avaliacao()
//                        .equalsIgnoreCase("N/A"))
//                .peek(e-> System.out.println("primeiro filtro(N/A)"+e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e-> System.out.println("Ordenaçao"+e))
//                .limit(10)
//                .peek(e-> System.out.println("Limite de 10"+e))
//                .map(e-> e.titulo().toUpperCase())
//                .peek(t-> System.out.println("Mapeamento"+t))
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream().flatMap(t->t.episodios().stream().map(e-> new Episodio(t.temporada(), e))).collect(Collectors.toList());

        episodios.forEach(System.out::println);
        System.out.println("####################################################################################################");
        System.out.println("Digite o trecho do titulo que deseja pesquisar");
        var varTrecho = leitura.nextLine();

        Optional<Episodio> episodioOptional = episodios.stream().filter(e -> e.getEpisodio().toLowerCase().contains(varTrecho.toLowerCase())).findFirst();
        if(episodioOptional.isPresent()){
            System.out.println("encontrado esse:");
            System.out.println("\nTemporada: "+ episodioOptional.get().getTemporada()+"\nEpisodio: "+episodioOptional.get().getEpisodio()+"\nNumero do Episodio: "+episodioOptional.get().getEpisodioNum()+"\nData de Lancamento: "+ episodioOptional.get().getDataLancamento());
        }else{
            System.out.println("Episodio nao encontrado!!!!");
        }

//        episodioOptional.ifPresent(System.out::println);
        ScannerInOut.proximoPasso();
    }













    public void mediaTemporada(){
        String filme = ScannerInOut.serieName();
        var json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+APIKEY);
        DataSerie dataSerie =  convertData.getData(json, DataSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dataSerie.totalTemporadas();i++){
            json = consumoApi.obterDados(ENDERECO+filme.replace(" ", "+")+"&season=" + i + APIKEY);
            DadosTemporada dadosTemporada =  convertData.getData(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }


        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporada(), d)))
                .collect(Collectors.toList());

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e->e.getAvaliacao()>0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble((Episodio::getAvaliacao))));

        System.out.println("Nome da Série "+dataSerie.nome()+"\nTotal de Temporadas "+dataSerie.totalTemporadas());
        System.out.println(avaliacoesPorTemporada);

        avaliacoesPorTemporada.forEach((temporada, mediaAvaliacao) -> {
            String valorFormatado = df.format(mediaAvaliacao);
            System.out.println("Temporada: " + temporada + ", Avaliação Média: " + valorFormatado);
        });


        DoubleSummaryStatistics estats = episodios.stream()
                .filter(e->e.getAvaliacao()>0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));


        System.out.println("Média: " + df.format(estats.getAverage()));
        System.out.println("Melhor episódio: " + estats.getMax());
        System.out.println("Pior episódio: " + estats.getMin());
        System.out.println("Quantidade de episodios Avaliados: " + estats.getCount());










        ScannerInOut.proximoPasso();
    }
    public void linkAPISearch(String link){
        var json = consumoApi.obterDados(link);
        System.out.println(json);
        ScannerInOut.proximoPasso();
    }
}