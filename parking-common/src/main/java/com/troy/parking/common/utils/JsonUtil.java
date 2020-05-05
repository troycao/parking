package com.troy.parking.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json转换工具类
 * 2020/3/10 0:05
 * @auhor troy
 **/
@Slf4j
public class JsonUtil {
    private static final ObjectMapper MAPPER;
    static {
        MAPPER  = new ObjectMapper();
        //反序列化时忽略大小写
        MAPPER.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        //序列化时保持原格式 如  getURL -> url  开启则保持 URL大写
        MAPPER.enable(MapperFeature.USE_STD_BEAN_NAMING);
    }
    /**
     * 将对象序列化为json字符串
     * @param data
     * @return
     */
    public static String toJsonString(Object data) {
        try {
            String result = MAPPER.writeValueAsString(data);
            return result;
        } catch (JsonProcessingException e) {
            log.error("Json转换错误",e);
        }
        return null;
    }

    /**
     * 将json字符串parse为java对象
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonData, Class<T> beanType) {
        try {
            T result = MAPPER.readValue(jsonData, beanType);
            return result;
        } catch (JsonMappingException e) {
            log.error("Json映射错误",e);
        } catch (JsonProcessingException e) {
            log.error("Json转换错误",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串parse为List
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> parseList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);

        try {
            List<T> resultList = MAPPER.readValue(jsonData, javaType);
            return resultList;
        } catch (JsonMappingException e) {
            log.error("Json映射错误",e);
        } catch (JsonProcessingException e) {
            log.error("Json转换错误",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 讲Json字符串parse为Map
     * @param jsonData
     * @param keyType
     * @param valueType
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> parseMap(String jsonData, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        try {
            Map<K, V> resultMap = MAPPER.readValue(jsonData, javaType);
            return resultMap;
        } catch (JsonMappingException e) {
            log.error("Json映射错误",e);
        } catch (JsonProcessingException e) {
            log.error("Json转换错误",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
