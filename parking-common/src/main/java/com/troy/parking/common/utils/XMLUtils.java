package com.troy.parking.common.utils;

import com.thoughtworks.xstream.XStream;

import java.util.concurrent.ConcurrentHashMap;

/**
 * XML转换器
 * @author troy
 */
public class XMLUtils {
    /**
     * XStream 缓存
     */
    private final static ConcurrentHashMap<Class,XStream> xStreamCache = new ConcurrentHashMap();
    /**
     * XML头部前缀
     */
    private final static String PREFIX_HEADER = "<?xml version=\"1.0\"encoding=\"UTF-8\"?>\n";
    private XMLUtils() {
    }

    /**
     * XML转java对象
     * @param xmlStr xml原文
     * @param root 根节点
     * @param clazz java对象类型
     * @param <E>
     * @return
     */
    public  static  <E> E fromXml(String xmlStr,String root,Class<E> clazz){
        XStream xStream = getXStream(clazz);
        xStream.alias(root,clazz);
        return (E)xStream.fromXML(xmlStr);
    }

    /**
     * XML转java对象 根节点默认为ROOT
     * @param xmlStr xml原文
     * @param clazz java对象类型
     * @param <E>
     * @return
     */
    public  static  <E> E fromXml(String xmlStr,Class<E> clazz){
        XStream xStream = getXStream(clazz);
        xStream.alias("ROOT",clazz);
        return (E)xStream.fromXML(xmlStr);
    }

    /**
     * java对象转XML String 根节点默认为ROOT
     * @param javaObject
     * @return
     */
    public static String toXmlStr(Object javaObject){
        if(javaObject==null){
            return "";
        }
        Class clazz = javaObject.getClass();
        XStream xStream = getXStream(clazz);
        xStream.alias("ROOT", clazz);
        return PREFIX_HEADER.concat(xStream.toXML(javaObject));
    }
    /**
     * java对象转XML String 可指定根节点
     * @param javaObject
     * @return
     */
    public static String toXmlStr(Object javaObject,String root){
        if(javaObject==null){
            return "";
        }
        Class clazz = javaObject.getClass();
        XStream xStream = getXStream(clazz);
        xStream.alias(root, clazz);
        return PREFIX_HEADER.concat(xStream.toXML(javaObject));
    }
    /**
     * 从缓存获取xstream对象
     * @param clazz
     * @return
     */
    private static XStream getXStream(Class clazz){
        XStream xStream = xStreamCache.get(clazz);
        if(xStream==null){
            xStream = new XStream();
            //设置安全策略
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypes(new Class[]{clazz});
            xStreamCache.putIfAbsent(clazz,xStream);
        }
        return xStream;
    }

}
