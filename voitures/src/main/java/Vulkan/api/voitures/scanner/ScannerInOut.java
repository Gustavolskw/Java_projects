package Vulkan.api.voitures.scanner;

import java.util.Scanner;

public class ScannerInOut {
    private static final Scanner teclado = new Scanner(System.in).useDelimiter("\n");
    public static String movieName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva o nome do filme:");
        return scanner.nextLine();
    }
    public static String serieName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva o nome da Serie:");
        return scanner.nextLine();
    }

    public static String serieNameSeasonEpisode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva o nome da série:");
        String name = scanner.nextLine();
        System.out.println("escreva a temporada.");
        int temp = scanner.nextInt();
        System.out.println("escreva o Episodio.");
        int episode = scanner.nextInt();

        String fullSearch;
        fullSearch = name.replace(" ", "+")+"&season="+temp+"&episode="+episode;

        return fullSearch;
    }
    public static String serieSeasonSearch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escreva no nome da serie:");
        String serie = scanner.nextLine();
        System.out.println("escreva a temporada:");
        int temp = scanner.nextInt();
        String fullSearch;
        fullSearch =serie+"&season="+temp;

        return fullSearch;
    }

    public static int pesquisaAno(){
        System.out.println("A partir de qual ano deseja pesquisar episodios?");
       int ano = teclado.nextInt();
       return ano;
    }


    public static int escolhaDeBusca(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                #####################################################################
                1- Filme
                2- Serie
                3- Episodio(Serie)
                4- Temporada(Serie)
                5- Teste
                6- Media Temporada
                7- Sair
                """);
        return scanner.nextInt();

    }
    public static void proximoPasso(){
        System.out.println("#######################################################################################");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


}
