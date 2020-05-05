package com.troy.parking.core.manager.impl;

import com.troy.parking.core.dao.ChannelInfoDao;
import com.troy.parking.core.entity.ChannelInfoEntity;
import com.troy.parking.core.manager.ChannelConfigManager;
import com.troy.parking.core.manager.enums.ChannelConfigEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 2020/3/12 21:55
 * @auhor troy
 **/
@Component
public class ChannelConfigManagerImpl implements ChannelConfigManager {

    @Autowired
    private ChannelInfoDao channelInfoDao;

    @Override
    public String getChannelConfigValue(String parkingName, ChannelConfigEnum channelConfigEnum) {
        List<ChannelInfoEntity> channelConfigs = channelInfoDao.selectChannelInfoByParkingName(parkingName);
        return channelConfigs.stream().filter(channelInfo -> {
            return channelConfigEnum.equals(channelInfo.getChannelKey());
        }).map(channelInfo-> channelInfo.getChannelValue()).findFirst().orElse(null);
    }
}
