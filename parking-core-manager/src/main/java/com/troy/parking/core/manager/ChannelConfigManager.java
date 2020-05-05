package com.troy.parking.core.manager;


import com.troy.parking.core.manager.enums.ChannelConfigEnum;

public interface ChannelConfigManager {
    public String getChannelConfigValue(String parkingName, ChannelConfigEnum channelConfigEnum);
}
