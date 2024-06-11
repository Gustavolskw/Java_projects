package Vulkan.api.voitures.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvertData {
   <T> T getData(String json, Class<T> classes) throws JsonProcessingException;
}
