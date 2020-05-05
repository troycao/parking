package com.troy.parking.core.manager;

import com.troy.parking.core.manager.dto.MerInfoDTO;

public interface MerInfoManager {

    MerInfoDTO getMerInfoByParkingId(String parkingId);

}
