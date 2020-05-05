package com.troy.parking.core.manager.enums;

import lombok.Getter;

@Getter
public enum ChannelConfigEnum {
    /**
     * 闸机传来的停车场名称，用来查询channel，跟据channel拿配置
     */
    CHANNEL_KEY("CHANNEL_NAME"),
    /**
     * 停车场全称
     */
    CHANNEL_PARKING_NAME_KEY("CHANNEL_PARKING_NAME"),
    /**
     * 闸机实现类
     */
    GATE_NAME_KEY("service_name"),
    /**
     * 验签key
     */
    SIGN_KEY("key"),
    /**
     * 闸机请求地址
     */
    GATE_URL_KEY("url"),
    /**
     * 无感停车，开票传参
     */
    ALI_PARKING_ID_KEY("alipay_parkId")
    ;
    ChannelConfigEnum(String configName) {
        this.configName = configName;
    }
    private String configName;
}
