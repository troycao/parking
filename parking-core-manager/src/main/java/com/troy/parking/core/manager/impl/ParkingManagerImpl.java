package com.troy.parking.core.manager.impl;


import com.troy.parking.common.utils.DomainUtil;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.manager.ParkingManager;
import com.troy.parking.core.manager.dto.ParkingOrderDTO;
import com.troy.parking.core.manager.enums.ParkingStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParkingManagerImpl implements ParkingManager {

    @Autowired
    private ParkingOrderDao parkingOrderDao;

    @Override
    public ParkingOrderDTO findParkingOrderById(String orderId){
        ParkingOrderEntity parkingOrderEntity = parkingOrderDao.selectById(orderId);
        return DomainUtil.map(parkingOrderEntity, ParkingOrderDTO.class);
    }

    @Override
    public void updateParkingFeeAndStatus(String parkingOrderId, ParkingStatusEnum parkingStatus, String parkingFee) {
        log.info("停车订单状态更新:{},停车费增加:{}",parkingStatus.getMsg(),parkingFee);
        int count = parkingOrderDao.updateParkingFeeAndStatus(parkingOrderId,parkingStatus.getCode(),parkingFee);
        log.info("{},更新{}",parkingOrderId,count);
    }
}
