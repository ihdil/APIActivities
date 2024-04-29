package Utiliti;

import Pojo.JsonPlaceHolderPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {
    public static <T>T convertJsonToJava(String strJson,Class<T> valueType) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(strJson, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}