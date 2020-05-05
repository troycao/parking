package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * @Author: troy
 * @Description: pojo
 */
@Data
public class ParkingInfoDTO {

   private GateChannelRespDTO parkingFeeResponse;
   private String status;
   private String parkingStatus;
}
