package View;

import Model.Cep;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writter {
    public static void fileWritter(Gson gson, ArrayList<Cep> listOfCep) throws IOException {
        FileWriter fileWriter = new FileWriter("Lista De Ceps.json");
        fileWriter.write(gson.toJson(listOfCep));
        fileWriter.close();
    }

}
