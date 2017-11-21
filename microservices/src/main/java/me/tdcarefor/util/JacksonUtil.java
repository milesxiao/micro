package me.tdcarefor.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by xxg on 16/9/26.
 */
public class JacksonUtil {
    private static ObjectMapper mapper;

    /**
     * 获取ObjectMapper实例
     * @param createNew
     *  方式：true，新实例；false,存在的mapper实例
     * @return
     */
    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        }
        return mapper;
    }


    public static String beanToJson(Object obj){
        String json="";
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {

        }
        return json;
    }


    public static Object jsonToBean(String json, Class<?> cls){
        Object vo=null;
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            vo = objectMapper.readValue(json, cls);
        } catch (Exception e) {
            System.out.println(e);
        }
        return vo;
    }

    public static Object jsonToBean(String json, TypeReference<?> jsonTypeReference){
        try {
            return  getMapperInstance(false).readValue(json, jsonTypeReference);
        } catch (Exception e) {
        }
        return null;
    }
}
