package com.troy.parking.core.dao;

import com.troy.parking.core.entity.ParkingOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-05-03 18:53:39
 */
@Mapper
public interface ParkingOrderDao extends BaseMapper<ParkingOrderEntity> {

    ParkingOrderEntity selectOneByCarNo(String carNo);

    ParkingOrderEntity queryOrderByParkingId(String id);

    ParkingOrderEntity searchParkingOrderByGateOrderId(String gateId);

    /**
     * 累计停车费 并更新停车状态
     * @param parkingOrderId
     * @param parkingStatus
     * @param parkingFee
     * @return
     */
    int updateParkingFeeAndStatus(@Param("parkingOrderId") String parkingOrderId, @Param("parkingStatus") String parkingStatus, @Param("parkingFee") String parkingFee);



}
