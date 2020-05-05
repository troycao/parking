package com.troy.parking.common.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

import java.util.Objects;

/**
 * 领域模型转换工具
 */
public class DomainUtil {
    private DomainUtil(){}
    private final static DozerBeanMapper INSTANCE;
    static {
        BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
            @Override
            protected void configure() {

            }
        };
        INSTANCE = new DozerBeanMapper();
        INSTANCE.addMapping(beanMappingBuilder);
    }
    public static  <T> T map(Object obj,Class<T> targetClass){
        if(Objects.isNull(obj)){
            return null;
        }
        return INSTANCE.map(obj,targetClass);
    }
}
