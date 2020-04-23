package com.troy.parking.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:54
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
