package com.troy.parking.core.api;

import com.troy.parking.core.api.bo.ParkingFeeResponseBO;

public interface GateService {

    /**
     * 获取停车费用
     */
    ParkingFeeResponseBO getParkingFee(String carNo);

}
