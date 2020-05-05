package com.troy.parking.core.api;

import java.util.Map;

/**
 * @Author: gaobaozong
 * @Description: 获取配置
 * @Date: Created in 19-6-17 - 下午2:41
 * @Version: V1.0
 */
public interface GateConfigService {

    ThreadLocal<Map<String,String>>  channelConfig = new ThreadLocal<>();

    default String getChannelCode(){
        Map<String,String> config = channelConfig.get();
        return config.get(ChannelInfoService.CHANNEL_KEY);
    }

    default void setChannelConfig(Map<String, String> config){
        channelConfig.remove();
        channelConfig.set(config);
    }

    default Map<String,String> getChannelConfig(){
        return channelConfig.get();
    }
}
