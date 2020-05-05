package com.troy.parking.core.manager;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: troy
 * @Description: 闸机token接口
 */
public interface IGateTokenService extends IGateConfigService {

    ScheduledThreadPoolExecutor clock = new ScheduledThreadPoolExecutor(16);

    Map<String, String> tokenMap = new ConcurrentHashMap<>();

    String generateToken();

    default String getToken(){
        String token = tokenMap.get(getChannelCode());
        if (StringUtils.isBlank(token)){
            clock.scheduleAtFixedRate(() -> {
                String _t = generateToken();
                tokenMap.put(getChannelCode(), _t);
                }, 0,setTokenTimeout(), TimeUnit.SECONDS);
            token = generateToken();
        }
        return token;
    }

    /**
     * token 超时设置
     * @return  秒
     */
    default int setTokenTimeout(){return 30*60;}
}
