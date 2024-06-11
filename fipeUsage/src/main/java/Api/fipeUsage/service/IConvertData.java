package Api.fipeUsage.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConvertData {
   <T> T obterDados(String json, Class<T> classe);

   <T> List<T> obterLista(String json, Class<T> classe);
}
