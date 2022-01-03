package com.belajar.movies.belajarspring.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> Object parse(Object obj, Class<T> clazz){
        Object returnObj =null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return (T) mapper.readValue((String) obj, clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnObj;
    }

    public static String toJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String response = "";
        try {
            response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (JsonGenerationException e){
            e.printStackTrace();
        }catch (JsonMappingException jm){
            jm.printStackTrace();
        }catch (Exception es){
            es.printStackTrace();
        }
        return response;
    }

}
