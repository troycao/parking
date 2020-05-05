package com.troy.parking.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-05-03 18:53:39
 */
@Data
@TableName("pims_parking_order")
public class ParkingOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String parkingName;
	/**
	 * 
	 */
	private String machineMer;
	/**
	 * 
	 */
	private String channelCode;
	/**
	 * 
	 */
	private String carNo;
	/**
	 * 
	 */
	private String vehicleInDate;
	/**
	 * 
	 */
	private String vehicleInTime;
	/**
	 * 
	 */
	private String vehileOutDate;
	/**
	 * 
	 */
	private String vehileOutTime;
	/**
	 * 
	 */
	private String stayTime;
	/**
	 * 
	 */
	private String parkingFee;
	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private String updateTime;
	/**
	 * 
	 */
	private String machineId;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String paymrntnr;
	/**
	 * 
	 */
	private String routeId;
	/**
	 * 
	 */
	private String parkingId;
	/**
	 * 
	 */
	private String gateOrderId;
	/**
	 * 
	 */
	private String deviceNo;

}
