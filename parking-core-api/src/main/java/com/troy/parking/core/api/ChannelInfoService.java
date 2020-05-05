package com.troy.parking.core.api;

import java.util.Map;
import java.util.Optional;

/**
 * @Author: troy
 * @Description: 获取闸机配置
 */
public interface ChannelInfoService {

    //闸机传来的停车场名称，用来查询channel，跟据channel拿配置
    String CHANNEL_KEY = "CHANNEL_NAME";
    //停车场全称
    String CHANNEL_PARKING_NAME_KEY = "CHANNEL_PARKING_NAME";
    //闸机实现类
    String GATE_NAME_KEY = "service_name";
    //验签key
    String SIGN_KEY = "key";
    //闸机请求地址
    String GATE_URL_KEY = "url";
    //无感停车，开票传参
    String ALI_PARKING_ID_KEY = "alipay_parkId";

    //会员系统圈圈的标识，要与数据库一致
    String OO_CHANNEL = "OO";

    String getChannelByParkingName(String parkingName);

    Map<String,String> getChannelConfigByChannel(String code);

    default boolean channelExist(String parkingName){
       String channel =  getChannelByParkingName(parkingName);
       if(channel == null){
           return false;
       }
       return true;
    }

    default  Map<String,String> getChannelConfigByParkingName(String parkName){
        String channel = getChannelByParkingName(parkName);
        return getChannelConfigByChannel(channel);
    }

    default  Map<String,String> getChannelConfigByOO(){
        String oo = OO_CHANNEL;
        return getChannelConfigByChannel(oo);
    }

    default String getSignKeyByParkingName(String name){
        return Optional.ofNullable(name)
                .map(this::getChannelConfigByParkingName)
                .map(_m -> _m.get(SIGN_KEY))
                .orElse(null);
    }
    default String getGateNameByParkingName(String name){
        return Optional.ofNullable(name)
                .map(this::getChannelConfigByParkingName)
                .map(_m -> _m.get(GATE_NAME_KEY))
                .orElse(null);
    }
    default String getMyParkingNameByParkingName(String name){
        return Optional.ofNullable(name)
                .map(this::getChannelConfigByParkingName)
                .map(_m -> _m.get(CHANNEL_PARKING_NAME_KEY))
                .orElse(null);
    }
    default String getGateUrlByParkingName(String name){
        return Optional.ofNullable(name)
                .map(this::getChannelConfigByParkingName)
                .map(_m -> _m.get(GATE_URL_KEY))
                .orElse(null);
    }
    default String getAliParkingIdByParkingName(String name){
        return Optional.ofNullable(name)
                .map(this::getChannelConfigByParkingName)
                .map(_m -> _m.get(ALI_PARKING_ID_KEY))
                .orElse(null);
    }

    default boolean isOpenNoninductive(String parkingName){
        return getAliParkingIdByParkingName(parkingName) != null;
    }

}
