package com.troy.parking.core.manager;

import com.troy.parking.core.manager.dto.ParkingOrderDTO;
import com.troy.parking.core.manager.enums.ParkingStatusEnum;

public interface ParkingManager {

    ParkingOrderDTO findParkingOrderById(String orderId);

    /**
     * 累加停车费 更新停车状态
     * @param parkingOrderId
     * @param parkingStatus
     * @param parkingFee
     */
    void updateParkingFeeAndStatus(String parkingOrderId, ParkingStatusEnum parkingStatus, String parkingFee);
}
