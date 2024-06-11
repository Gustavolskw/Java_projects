package Vulkan.api.voitures.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> classe){
        try {
            return mapper.readValue(json, classe);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
