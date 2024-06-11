package Control;

import Model.Cep;
import Model.CepViaCep;
import Model.Connection;
import View.Writter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public void start() throws IOException {
        ArrayList<Cep> listOfCeps = new ArrayList<>();
        Scanner leitura = new Scanner(System.in);
        Connection connection = new Connection();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
        String cep = "";


        while (!cep.equalsIgnoreCase("sair")) {

            System.out.println("Digite um CEP:  ");
            cep = leitura.nextLine();

            if (cep.equalsIgnoreCase("sair")) {
                break;
            }
            CepViaCep cepViaCep = connection.apiConnection(cep);
            Cep cepJson = new Cep(cepViaCep);
            System.out.println(cepJson);
            listOfCeps.add(cepJson);

            System.out.println(listOfCeps);
            Writter.fileWritter(gson, listOfCeps);
            System.out.println("Programa finalizou com exito");

        }
    }
}
