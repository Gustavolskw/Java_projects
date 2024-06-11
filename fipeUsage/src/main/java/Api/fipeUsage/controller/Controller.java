package Api.fipeUsage.controller;

import Api.fipeUsage.model.Dados;
import Api.fipeUsage.model.DadosAnos;
import Api.fipeUsage.model.Modelos;
import Api.fipeUsage.model.Veiculo;
import Api.fipeUsage.service.ConsumoApi;
import Api.fipeUsage.service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Scanner leitura = new Scanner(System.in);
    private Scanner codigo = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConvertData conversor = new ConvertData();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void Start(){
        var menu = """
               *** OPÇÕES ***
               1- Carro
               2- Moto
               3- Caminhão
               
               Digite uma das opções para consulta: 
               """;

        System.out.println(menu);
        var opcao = codigo.nextInt();
        while(opcao>3){
            opcao = codigo.nextInt();
        }
        String endereco;
        if (opcao==1 ){
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao==2) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        int codigoMarca = codigo.nextInt();

        Optional<Dados>marcaEscolhida = marcas.stream().filter(e->e.codigo()==codigoMarca).findAny();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);

        if (marcaEscolhida.isPresent()) {
            System.out.println("\nModelos Da Marca : " +marcaEscolhida.get().nome());
        }else {
            System.out.println("\nModelos Dessa Marca");
        }
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<DadosAnos> anos = conversor.obterLista(json, DadosAnos.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }

}
