package com.troy.parking.core.manager.impl;

import com.troy.parking.common.utils.DomainUtil;
import com.troy.parking.core.dao.MerInfoDao;
import com.troy.parking.core.entity.MerInfoEntity;
import com.troy.parking.core.manager.MerInfoManager;
import com.troy.parking.core.manager.dto.MerInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerInfoManagerImpl implements MerInfoManager {

    @Autowired
    MerInfoDao merInfoDao;

    @Override
    public MerInfoDTO getMerInfoByParkingId(String parkingId) {
        MerInfoEntity pimsMerInfo = merInfoDao.selectMerInfoByParkingId(parkingId);
        return DomainUtil.map(pimsMerInfo, MerInfoDTO.class);
    }
}
