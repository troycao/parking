package com.troy.parking.core.manager;

import com.troy.parking.core.manager.enums.ChannelConfigEnum;

import java.util.Map;

/**
 * @Author: troy
 * @Description: 获取配置
 */
public interface IGateConfigService {

    ThreadLocal<Map<String,String>>  channelConfig = new ThreadLocal<>();

    default String getChannelCode(){
        Map<String,String> config = channelConfig.get();
        return config.get(ChannelConfigEnum.CHANNEL_KEY.getConfigName());
    }

    default void setChannelConfig(Map<String, String> config){
        channelConfig.remove();
        channelConfig.set(config);
    }

    default Map<String,String> getChannelConfig(){
        return channelConfig.get();
    }
}
